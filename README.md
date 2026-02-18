# ai-dost

AI-first job preparation platform designed as a Java 21 + Spring Boot microservices system.

## Architecture Overview

| Service | Port | Base Path | Responsibility |
|---|---:|---|---|
| service-registry | 8761 | n/a | Eureka discovery registry for all services |
| api-gateway-service | 8080 | `/` | single-entry API gateway routing to all microservices |
| auth-service | 8081 | `/auth` | registration/login, JWT issuance, plan + role claims |
| user-profile-service | 8082 | `/profile` | candidate profile, goals, stack, preferences |
| dsa-service | 8083 | `/dsa` | curated DSA problems + per-user states/notes |
| mock-service | 8084 | `/mock` | mock sessions, question/answer records |
| project-experience-service | 8085 | `/project`, `/experience` | projects and work-experience narratives |
| resume-ats-service | 8086 | `/resume` | structured resume + ATS generation stub |
| readiness-service | 8087 | `/readiness` | rule-based readiness score aggregation |
| ai-gateway-service | 8088 | `/ai-gateway` | provider-agnostic AI operations routing |

## JWT and Security Model
- Auth Service issues JWT with `sub` (userId), `roles`, and `plan` claims.
- Downstream services can validate JWT (stubbed security setup included), extract claims, and gate endpoints via `@PreAuthorize`.
- Roles supported: `CANDIDATE`, `MENTOR`, `RECRUITER`, `ADMIN`.
- Plans supported: `FREE`, `PRO`.

## AI Gateway Integration Pattern
- DSA, Mock, Project/Experience, Resume services should call AI Gateway through stable DTO contracts.
- AI Gateway exposes operation-specific methods and routes internally by `(operation, tier)` to `(provider, model)`.
- Current implementation is stub-based so business flows are not blocked by external AI keys.

## Run
```bash
mvn clean install
```
Start services in this order for zero-config UI integration:
```bash
# 1) service registry
cd service-registry && mvn spring-boot:run

# 2) api gateway
cd ../api-gateway-service && mvn spring-boot:run

# 3) business microservices (each in a new terminal)
cd ../auth-service && mvn spring-boot:run
cd ../user-profile-service && mvn spring-boot:run
cd ../dsa-service && mvn spring-boot:run
cd ../mock-service && mvn spring-boot:run
cd ../project-experience-service && mvn spring-boot:run
cd ../resume-ats-service && mvn spring-boot:run
cd ../readiness-service && mvn spring-boot:run
cd ../ai-gateway-service && mvn spring-boot:run
```
After services are up, the UI can call only `http://localhost:8080` (gateway) without per-service URL edits.


## DSA Service API Examples

Get master list:
```bash
curl -X GET http://localhost:8083/dsa/problems
```

Get current user's problem view (stub userId extraction):
```bash
curl -X GET http://localhost:8083/dsa/me/problems
```

Update status:
```bash
curl -X PUT http://localhost:8083/dsa/me/problems/1/status   -H "Content-Type: application/json"   -d '{"status":"DONE"}'
```

Update notes:
```bash
curl -X PUT http://localhost:8083/dsa/me/problems/1/notes   -H "Content-Type: application/json"   -d '{"notes":"Revisit two-pointer optimization"}'
```

## Web UI (React + TypeScript)

A frontend scaffold is available under `ui/` using **Vite + React + TypeScript + React Router + TanStack Query + TailwindCSS**.

### Install and run
```bash
cd ui
npm install
npm run dev
```

### Configure API base URLs
1. Copy env template:
```bash
cp ui/.env.example ui/.env
```
2. Set values in `ui/.env`.

You can either:
- set `VITE_USE_API_GATEWAY=true` and route all calls through `VITE_API_GATEWAY_URL` (recommended; default works with this repo), or
- set `VITE_USE_API_GATEWAY=false` and call each microservice URL directly (`VITE_DSA_SERVICE_URL`, `VITE_AUTH_SERVICE_URL`, etc.).

### Auth behavior
- JWT token is stored in localStorage for now (temporary; TODO to migrate to httpOnly cookie strategy via backend gateway).
- Axios interceptors attach `Authorization: Bearer <token>` automatically.

### Main routes
- `/login`, `/register`
- `/dashboard`
- `/dsa`, `/dsa/:problemId`
- `/profile`
- `/mock`
- `/projects`
- `/experience`

After login/register, open Dashboard and navigate to DSA/Profile/Mock/Projects/Experience from the top nav.
