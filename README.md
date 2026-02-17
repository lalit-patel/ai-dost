# ai-dost

AI-first job preparation platform designed as a Java 21 + Spring Boot microservices system.

## Architecture Overview

| Service | Port | Base Path | Responsibility |
|---|---:|---|---|
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
Run a service:
```bash
cd dsa-service
mvn spring-boot:run
```

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
