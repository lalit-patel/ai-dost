# ai-dost (localhost development)

This repo is a multi-module Spring Boot microservices system that can be run **fully on localhost without Docker**.

## Services and ports

| Service | Port | Base path |
|---|---:|---|
| service-registry | 8761 | n/a |
| api-gateway-service | 8080 | `/` |
| auth-service | 8081 | `/auth` |
| user-profile-service | 8082 | `/profile` |
| dsa-service | 8083 | `/dsa` |
| mock-service | 8084 | `/mock` |
| project-experience-service | 8085 | `/project`, `/experience` |
| resume-ats-service | 8086 | `/resume` |
| readiness-service | 8087 | `/readiness` |
| ai-gateway-service | 8088 | `/ai-gateway` |
| UI (Vite) | 5173 | `/` |

---

## 1) Prerequisites

- Java 17+
- Maven 3.9+
- Node.js 18+
- PostgreSQL 14+

---

## 2) Local PostgreSQL setup (no Docker)

### Create DB
```bash
psql -U postgres -f db/local/01-create-database.sql
```

### Create tables
```bash
psql -U postgres -d ai_dost -f db/local/02-create-tables.sql
```

> Default local credentials used by all services:
> - username: `postgres`
> - password: `postgres`
> - db: `ai_dost`
>
> Override with environment variables if needed:
> `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`

---

## 3) Build everything

From repo root:
```bash
mvn clean package -DskipTests
```

---

## 4) Start services (order matters)

Open a separate terminal per service.

### 4.1 Eureka
```bash
mvn -pl service-registry spring-boot:run
```

### 4.2 Backend services
```bash
mvn -pl auth-service spring-boot:run
mvn -pl user-profile-service spring-boot:run
mvn -pl dsa-service spring-boot:run
mvn -pl mock-service spring-boot:run
mvn -pl project-experience-service spring-boot:run
mvn -pl resume-ats-service spring-boot:run
mvn -pl readiness-service spring-boot:run
mvn -pl ai-gateway-service spring-boot:run
```

### 4.3 API gateway
```bash
mvn -pl api-gateway-service spring-boot:run
```

---

## 5) Start UI

```bash
cd ui
cp .env.local.example .env.local
npm install
npm run dev
```

UI runs at `http://localhost:5173` and targets API gateway at `http://localhost:8080`.

---

## 6) Verify system health

- Eureka dashboard: `http://localhost:8761`
- Gateway actuator: `http://localhost:8080/actuator/health`
- Auth via gateway: `http://localhost:8080/auth/login`

You should see all backend services registered in Eureka.

---

## 7) Test registration/login flow

### Register
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"displayName":"Local User","email":"local.user@example.com","password":"Pass@123"}'
```

### Login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"local.user@example.com","password":"Pass@123"}'
```

### Validate `/auth/me`
```bash
TOKEN="<token from login>"
curl http://localhost:8080/auth/me -H "Authorization: Bearer $TOKEN"
```

---

## Notes

- All services are configured for localhost Eureka (`http://localhost:8761/eureka`).
- CORS is enabled for UI origins `http://localhost:5173` and `http://localhost:3000`.
- API Gateway routes all frontend paths to proper services.
