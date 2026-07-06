# MRB Exam Management — Backend

Spring Boot REST API for the **Maharashtra Rashtrabhasha Sabha (MRB)** exam system. Powers student registration, exam configuration, applications, hall tickets, results, analytics, and document storage.

**Frontend:** [`../MRB-DEMO-FRONTEND-main`](../MRB-DEMO-FRONTEND-main)

**Source root:** `webapp/` (Maven project lives in the nested `webapp/` folder)

---

## Quick start

```bash
cd webapp/webapp
cp .env.example .env   # if present; otherwise set env vars (see Configuration)
./mvnw spring-boot:run # http://localhost:8080
./mvnw test
```

**Requirements:** Java 17, Maven 3.6+, PostgreSQL, Redis (optional), MinIO (optional)

Health check: `GET /ping`

---

## What this API does

### Core capabilities

| Area | Description |
|------|-------------|
| **Students** | Register, search, update, delete; link to schools |
| **Student profiles** | Extended profile data per student |
| **Auth** | Student login (`email` + password) |
| **Exams** | Exam definitions: papers, fees, schedule, status |
| **Applications** | Submit, search, update status; hall ticket + roll number generation |
| **Results** | Publish marks; query by student or filters |
| **Master data** | Regions → Exam centres → Schools hierarchy |
| **Analytics** | Dashboard summary and entity counts |
| **File storage** | Upload/delete documents via MinIO |
| **Admin stats** | Legacy stats endpoint |

### Cross-cutting features

- **Pagination** on list/search endpoints (`page`, `size`, `sort`)
- **Filtering** via JPA Specifications + filter DTOs per domain
- **DTO mapping** in service layer (entities never leak raw to API)
- **Redis caching** (configurable; can be disabled)
- **CORS** for frontend origins
- **Centralized exceptions** via `@RestControllerAdvice`

---

## Architecture

Layered **domain packages** under `com.rasthrabhasha`:

```
HTTP Request
     │
     ▼
Controller   ← REST mapping, validation, pagination params
     │
     ▼
Service      ← Business logic, DTO mapping, workflows
     │
     ▼
Repository   ← Spring Data JPA
     │
     ▼
Entity       ← Hibernate / PostgreSQL
```

Optional: **MinioStorageService** ← **FileUploadController** for `/files/*`

### Package map

| Package | Responsibility |
|---------|----------------|
| `student` | `Student`, `StudentProfile`, CRUD + search |
| `auth` | `AuthService.verifyStudent` → `POST /auth/student/login` |
| `exam` | Exam CRUD + search |
| `application` | Applications, hall ticket batch/single generation |
| `result` | Result CRUD + student result lookup |
| `region` | Region CRUD |
| `examcentre` | Exam centre CRUD (belongs to region) |
| `school` | School CRUD (belongs to exam centre) |
| `analytics` | Summary + count endpoints |
| `admin` | Admin stats |
| `storage` | MinIO upload/delete |
| `health` | `/ping` |
| `exception` | Global error handling |
| `common` | Shared DTOs (`PageResponse`), `Address` embeddable |

Each domain typically contains:

```
Entity.java
Repository.java
Service.java          ← mapToDTO, search*, CRUD
Controller.java
dto/                  ← Request/response DTOs
specification/        ← Dynamic query filters (where used)
```

### Important workflows

**Hall ticket generation** (`ExamApplicationService`):

1. Admin triggers `POST /exam-applications/{id}/generate-hall-ticket` or batch endpoint.
2. Service assigns roll number, updates application status, returns DTO.

**Application lifecycle:**

- Student submits via `POST /exam-applications`.
- Admin searches/filters via `GET /exam-applications`.
- Status transitions: `PENDING` → `APPROVED` / `REJECTED`.

**Data hierarchy:**

```
Region
  └── ExamCentre
        └── School
              └── Student
                    └── ExamApplication → ExamResult
```

---

## API reference (primary routes)

Legacy routes (`/getAllStudents`, `/addSchool`, etc.) exist alongside RESTful ones. Prefer the REST paths for new clients.

### Auth

| Method | Path | Purpose |
|--------|------|---------|
| POST | `/auth/student/login` | Student login |

### Students

| Method | Path | Purpose |
|--------|------|---------|
| POST | `/students?schoolId=` | Create student |
| GET | `/students` | Search (paginated) |
| GET | `/students/{id}` | Get by ID (via legacy `getStudentById` too) |
| PUT | `/students/{id}` | Update |
| DELETE | `/students/{id}` | Delete |

### Student profiles

| Method | Path | Purpose |
|--------|------|---------|
| GET/POST/PUT/DELETE | `/studentProfiles`, `/studentProfiles/{id}` | CRUD + search |
| GET | `/studentprofile/studentId/{id}` | Profile by student |

### Exams

| Method | Path | Purpose |
|--------|------|---------|
| POST | `/exams` | Create |
| GET | `/exams` | Search (paginated) |
| GET | `/exams/{id}` | Get one |
| GET | `/exams/all` | List all (legacy) |
| PUT | `/exams/{id}` | Update |
| DELETE | `/exams/{id}` | Delete |

### Applications

| Method | Path | Purpose |
|--------|------|---------|
| POST | `/exam-applications` | Submit application |
| GET | `/exam-applications` | Search (paginated) |
| PUT | `/exam-applications/{id}` | Update (status, etc.) |
| DELETE | `/exam-applications/{id}` | Delete |
| POST | `/exam-applications/{id}/generate-hall-ticket` | Single hall ticket |
| POST | `/exam-applications/batch-generate-hall-tickets` | Batch hall tickets |

### Results

| Method | Path | Purpose |
|--------|------|---------|
| POST | `/exam-results` | Create result |
| GET | `/exam-results` | Search (paginated) |
| GET | `/getStudentResults` | Results for a student |
| PUT | `/exam-results/{id}` | Update |
| DELETE | `/exam-results/{id}` | Delete |

### Master data

| Resource | Base path |
|----------|-----------|
| Regions | `/regions` |
| Exam centres | `/exam-centres` |
| Schools | `/schools`, `/schools/{id}` |

### Analytics

| Method | Path | Purpose |
|--------|------|---------|
| GET | `/summary` | Dashboard summary |
| GET | `/counts/school/{id}/students` | Students in school |
| GET | `/counts/region/{id}/students` | Students in region |
| GET | `/counts/region/{id}/schools` | Schools in region |
| GET | `/counts/region/{id}/exam-centres` | Centres in region |
| GET | `/counts/exam-centre/{id}/schools` | Schools in centre |
| GET | `/counts/exam-centre/{id}/students` | Students in centre |

### Files

| Method | Path | Purpose |
|--------|------|---------|
| POST | `/files/upload` | Upload to MinIO |
| DELETE | `/files/upload` | Delete from MinIO |

### Pagination

```
GET /students?page=0&size=20&sort=firstName,asc
```

Filter params vary by domain (see `*FilterDTO` classes in each package).

---

## Tech stack

| Layer | Choice |
|-------|--------|
| Framework | Spring Boot 3.2.2 |
| Language | Java 17 |
| Database | **PostgreSQL** (via env vars) |
| ORM | Spring Data JPA / Hibernate |
| Cache | Spring Cache + Redis |
| Storage | MinIO 8.5.7 |
| Config | spring-dotenv (`.env` support) |
| Build | Maven |
| Deploy | Docker (`Dockerfile`), Render (`render.yaml`) |

---

## Configuration

Set in `webapp/src/main/resources/application.properties` (values from environment):

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=${DB_DRIVER}

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

spring.data.redis.url=${REDIS_URL}
spring.cache.type=
spring.cache.redis.time-to-live=${CACHE_TIME_TO_LIVE}

minio.url=${MINIO_URL}
minio.access-key=${MINIO_ACCESS_KEY}
minio.secret-key=${MINIO_SECRET_KEY}
minio.bucket=${MINIO_BUCKET}
```

Do **not** commit real credentials. Use `.env` locally and platform secrets in production.

---

## Project layout

```
webapp/
├── Dockerfile
├── render.yaml
├── README.md
└── webapp/                          ← Maven module
    ├── pom.xml
    ├── seed_data.py                 ← optional DB seed script
    └── src/main/java/com/rasthrabhasha/
        ├── WebappApplication.java   ← @SpringBootApplication, @EnableCaching
        ├── CorsConfig.java
        ├── student/
        ├── auth/
        ├── exam/
        ├── application/
        ├── result/
        ├── region/
        ├── examcentre/
        ├── school/
        ├── analytics/
        ├── admin/
        ├── storage/
        ├── health/
        ├── exception/
        └── common/
    └── src/test/java/               ← Service + filter DTO tests
```

---

## Docker

```bash
cd webapp/webapp
./mvnw package
cd ..
docker build -t mrb-webapp .
docker run -p 8080:8080 --env-file webapp/.env mrb-webapp
```

---

## Security (current state)

- **No Spring Security / JWT** in this demo.
- Student auth is a simple credential check returning a DTO.
- Admin endpoints are open.
- For production: add Spring Security, RBAC, password hashing audit, and secure MinIO policies.

---

## License

Proprietary — Maharashtra Rashtrabhasha Sabha
