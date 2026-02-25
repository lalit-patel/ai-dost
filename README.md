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

## Run Locally

### Prerequisites
- Java 21
- Maven 3.9+
- Docker + Docker Compose

### 1) Build all backend services
```bash
mvn clean package -DskipTests
```

### 2) Start backend services with Docker Compose
```bash
docker compose up --build
```

This starts:
- PostgreSQL on `localhost:5432`
- Eureka Service Registry on `localhost:8761`
- API Gateway on `localhost:8080`
- All backend microservices (`8081`–`8088`)

### 3) Run UI locally (recommended)
```bash
cd ui
npm install
npm run dev
```

UI runs at `http://localhost:5173` and calls API Gateway at `http://localhost:8080` by default.

### 4) Optional: run UI in Docker too
```bash
docker compose --profile ui up --build
```

This enables the `ui` service and serves it at `http://localhost:3000`.

### 5) Optional AI provider keys
The system runs with local/stubbed AI routing by default. If you want to use real providers, set these before `docker compose up` (or `docker compose --profile ui up`):
- `OPENAI_API_KEY`
- `ANTHROPIC_API_KEY`

### Troubleshooting
- If Maven cannot download dependencies, verify your network/firewall allows access to `https://repo.maven.apache.org/maven2`.
- Ensure ports `3000`, `5432`, `8080`–`8088`, and `8761` are free.
- On Windows, ensure Docker Desktop is running before `docker compose up --build` and that it is configured to use Linux containers/WSL2.
- If you see `open //./pipe/docker_engine: The system cannot find the file specified`, start Docker Desktop (or the Docker Engine service) and retry from an elevated terminal.
