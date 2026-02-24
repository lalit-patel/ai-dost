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


## One-command production-like run (no local edits)
Use Docker Compose to run UI + API Gateway + Eureka + all microservices + PostgreSQL with default production-like wiring:

```bash
docker compose up --build
```

Endpoints after startup:
- UI: `http://localhost:3000`
- API Gateway: `http://localhost:8080`
- Service Registry (Eureka): `http://localhost:8761`

This setup is zero-edit by default:
- UI is preconfigured to call only API Gateway.
- Services auto-register in Eureka.
- Gateway routes to all services.
- Database config is injected via environment.

To enable real AI providers, set keys before startup (optional):
```bash
export OPENAI_API_KEY=<your_key>
export ANTHROPIC_API_KEY=<your_key>
# optional provider override (default is LOCAL for no-key boot)
export AI_ROUTE_EXPLAIN_PROVIDER=OPENAI
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


### Maven fix for `spring-cloud-dependencies:pom:unknown`
If Maven cached a bad resolution, force refresh:
```bash
mvn -U -pl ai-gateway-service -am clean package
```
If it still persists, clear the local cached metadata and retry:
```bash
rm -rf ~/.m2/repository/org/springframework/cloud/spring-cloud-dependencies
mvn -U -pl ai-gateway-service -am clean package
```

## Production AI provider configuration
`ai-gateway-service` supports real providers (OpenAI / Anthropic) via API keys and model routing, with `LOCAL` defaults so the full stack still boots without keys.

Set environment variables before starting `ai-gateway-service`:
```bash
export OPENAI_API_KEY=<your_openai_key>
export ANTHROPIC_API_KEY=<your_anthropic_key>
```

Routing is configurable in `ai-gateway-service/src/main/resources/application.yml` under `aidost.ai.routes`.
If keys are missing for a configured provider, the service fails fast with a clear error.

## Free hosting deployment (Render Blueprint)
I added `render.yaml` so you can deploy the full stack on Render free tier without editing service code.

### Steps
1. Push this repository to GitHub.
2. In Render, choose **New + > Blueprint** and select this repo.
3. Render will create:
   - `ui` static site,
   - `service-registry`, `api-gateway-service`, and all backend services,
   - managed PostgreSQL database (`ai-dost-db`).
4. After deploy, open the `ui` service URL.

### Optional AI keys
Set these in Render dashboard for `ai-gateway-service` to enable real providers:
- `OPENAI_API_KEY`
- `ANTHROPIC_API_KEY`

Without keys, app still runs using `LOCAL` AI provider routes.

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

For plug-and-play setup keep `VITE_USE_API_GATEWAY=true` and `VITE_API_GATEWAY_URL=http://localhost:8080`.

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



## Production branch strategy (`main` as source of truth)
To use `main` as the only production branch:

```bash
# from current working branch (example: work)
git checkout work
git checkout -B main

# if remote exists
git push -u origin main
```

If your repository already has a separate `main`, merge your current branch into it:

```bash
git checkout main
git merge work
git push origin main
```

Then set `main` as the default branch in your Git hosting settings and use it for production deployments.


### If merge conflict happens while promoting to `main`
```bash
git checkout main
git merge work
# if conflict:
git status
# edit conflicted files and keep the correct final content
# then mark resolved:
git add <resolved-files>
git commit -m "resolve merge conflicts from work into main"
git push origin main
```

Tip: enable recorded conflict resolution to speed up repeated merges:
```bash
git config --global rerere.enabled true
```

## Low-conflict contribution policy
To reduce merge conflicts and review overhead:
- For non-code/support queries, update docs only (prefer `README.md`).
- Do not touch unrelated service/module files in the same PR.
- Keep each PR scoped to a single issue and minimal file set.



## Single-branch workflow (direct push to `main`)
If your repository now uses only `main`, use this flow (no merge request needed):

```bash
git checkout main
git pull origin main
# make changes
git add .
git commit -m "your message"
git push origin main
```

If your local branch is not `main` yet:
```bash
git branch -M main
git push -u origin main
```
