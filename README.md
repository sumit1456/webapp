# MRB Exam Management System - Backend

A comprehensive Spring Boot backend application for the Maharashtra Rashtrabhasha Sabha (MRB) Exam Management System. This system provides RESTful APIs for managing Hindi language proficiency examinations (Rashtrabhasha Pravin Pariksha) including student registration, exam applications, result publishing, and certificate generation.

## 🚀 Project Overview

This is a robust, scalable backend service built with Spring Boot 3.2.2, Java 17, and MySQL. It provides comprehensive REST APIs for:

- Student management and authentication
- Exam creation and configuration
- Exam application processing
- Result publishing and management
- School, region, and exam center administration
- Analytics and reporting
- Document storage with MinIO

## ✨ Key Features

### Core Features
- **RESTful APIs**: Comprehensive REST endpoints for all entities
- **Pagination**: Built-in pagination support for large datasets
- **Search & Filtering**: Advanced search capabilities with JPA Specifications
- **Caching**: Redis-based caching for improved performance
- **File Storage**: MinIO integration for document storage
- **Cross-Origin Support**: CORS configuration for frontend integration
- **Exception Handling**: Centralized exception handling with custom responses

### Entity Management
- **Students**: Complete CRUD operations with profile management
- **Exams**: Exam creation with papers, fees, schedules, and rules
- **Applications**: Exam application submission and approval workflow
- **Results**: Result publishing with marksheet data
- **Schools**: School management with region association
- **Regions**: Geographic region management
- **Exam Centres**: Examination center configuration
- **Admins**: Administrator user management

### Analytics
- Student statistics by region
- Application trends and metrics
- Result distribution analysis
- Dashboard analytics summary

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.2.2
- **Java Version**: Java 17
- **Database**: MySQL (Clever Cloud)
- **ORM**: Spring Data JPA with Hibernate
- **Caching**: Spring Cache with Redis
- **File Storage**: MinIO 8.5.7
- **Build Tool**: Maven
- **API Documentation**: RESTful design (Swagger can be added)

## 📁 Project Structure

```
src/main/java/com/rasthrabhasha/
├── WebappApplication.java       # Main Spring Boot application entry point
├── CorsConfig.java              # CORS configuration for frontend
├── admin/                      # Admin user management
│   ├── Admin.java              # Admin entity
│   ├── AdminController.java   # Admin REST endpoints
│   ├── AdminRepository.java    # Admin JPA repository
│   ├── AdminService.java       # Admin business logic
│   └── dto/                    # Admin data transfer objects
├── analytics/                  # Analytics and reporting
│   ├── AnalyticsController.java # Analytics REST endpoints
│   ├── AnalyticsService.java    # Analytics business logic
│   └── dto/                     # Analytics data transfer objects
├── application/                # Exam application management
│   ├── ExamApplication.java      # Application entity
│   ├── ExamApplicationController.java # Application REST endpoints
│   ├── ExamApplicationRepository.java # Application JPA repository
│   ├── ExamApplicationService.java    # Application business logic
│   ├── specification/           # JPA specifications for filtering
│   └── dto/                     # Application data transfer objects
├── auth/                       # Authentication utilities
├── common/                     # Shared components
│   ├── Address.java            # Address entity (embedded)
│   └── dto/                    # Common DTOs (PageResponse)
├── exam/                       # Exam management
│   ├── Exam.java               # Exam entity
│   ├── ExamController.java     # Exam REST endpoints
│   ├── ExamRepository.java     # Exam JPA repository
│   ├── ExamService.java        # Exam business logic
│   ├── specification/          # JPA specifications for filtering
│   └── dto/                    # Exam data transfer objects
├── examcentre/                 # Exam center management
│   ├── ExamCentre.java         # Exam center entity
│   ├── ExamCentreController.java # Exam center REST endpoints
│   ├── ExamCentreRepository.java # Exam center JPA repository
│   ├── ExamCentreService.java    # Exam center business logic
│   ├── specification/           # JPA specifications for filtering
│   └── dto/                    # Exam center data transfer objects
├── exception/                  # Exception handling
├── redis/                      # Redis configuration
├── region/                     # Region management
│   ├── Region.java             # Region entity
│   ├── RegionController.java   # Region REST endpoints
│   ├── RegionRepository.java   # Region JPA repository
│   ├── RegionService.java      # Region business logic
│   ├── specification/          # JPA specifications for filtering
│   └── dto/                    # Region data transfer objects
├── result/                     # Result management
│   ├── ExamResult.java         # Result entity
│   ├── ExamResultController.java # Result REST endpoints
│   ├── ExamResultRepository.java # Result JPA repository
│   ├── ExamResultService.java    # Result business logic
│   ├── specification/           # JPA specifications for filtering
│   └── dto/                    # Result data transfer objects
├── school/                     # School management
│   ├── School.java             # School entity
│   ├── SchoolController.java   # School REST endpoints
│   ├── SchoolRepository.java   # School JPA repository
│   ├── SchoolService.java      # School business logic
│   ├── specification/          # JPA specifications for filtering
│   └── dto/                    # School data transfer objects
├── storage/                    # MinIO file storage
├── student/                    # Student management
│   ├── Student.java            # Student entity
│   ├── StudentController.java  # Student REST endpoints
│   ├── StudentRepository.java  # Student JPA repository
│   ├── StudentService.java     # Student business logic
│   ├── StudentProfile.java     # Student profile entity
│   ├── StudentProfileController.java # Profile REST endpoints
│   ├── StudentProfileRepository.java # Profile JPA repository
│   ├── StudentProfileService.java    # Profile business logic
│   ├── specification/         # JPA specifications for filtering
│   └── dto/                    # Student data transfer objects
└── webapp/                     # Additional webapp utilities

src/main/resources/
└── application.properties      # Application configuration
```

## 📄 Main Components Explained

### WebappApplication.java
- **Purpose**: Main Spring Boot application entry point
- **Functionality**: Bootstraps the Spring Boot application with caching enabled
- **Key Features**: 
  - `@SpringBootApplication` annotation for auto-configuration
  - `@EnableCaching` for Redis cache support
  - Runs on default port 8080

### StudentController.java
- **Purpose**: REST API endpoints for student management
- **Endpoints**:
  - `POST /students` - Create new student
  - `GET /students` - Search/filter students with pagination
  - `GET /students/{id}` - Get student by ID
  - `PUT /students/{id}` - Update student
  - `DELETE /students/{id}` - Delete student
  - `GET /getAllStudents` - Get all students (legacy)
  - `GET /getStudentById` - Get student by ID (legacy)
- **Key Features**:
  - Pagination support with Spring Data
  - Advanced filtering with StudentFilterDTO
  - School association during creation

### ExamController.java
- **Purpose**: REST API endpoints for exam management
- **Endpoints**:
  - `POST /exams` - Create new exam
  - `GET /exams` - Search/filter exams with pagination
  - `GET /exams/{id}` - Get exam by ID
  - `PUT /exams/{id}` - Update exam
  - `DELETE /exams/{id}` - Delete exam
  - `GET /exams/all` - Get all exams (legacy)
- **Key Features**:
  - CORS enabled for cross-origin requests
  - Complex exam configuration (papers, fees, dates)
  - Pagination and filtering support

### ExamApplicationController.java
- **Purpose**: REST API endpoints for exam application management
- **Endpoints**:
  - `POST /exam-applications` - Submit exam application
  - `GET /exam-applications` - Search/filter applications with pagination
  - `GET /exam-applications/{id}` - Get application by ID
  - `PUT /exam-applications/{id}` - Update application
  - `DELETE /exam-applications/{id}` - Delete application
  - `POST /exam-applications/{id}/generate-hall-ticket` - Generate hall ticket
  - `POST /exam-applications/batch-generate-hall-tickets` - Batch generate hall tickets
- **Key Features**:
  - Application workflow management
  - Hall ticket generation with roll number assignment
  - Batch operations for efficiency
  - Status tracking (PENDING, APPROVED, REJECTED)

### AnalyticsController.java
- **Purpose**: REST API endpoints for analytics and reporting
- **Endpoints**:
  - Various analytics endpoints for dashboard metrics
- **Key Features**:
  - Student count by region
  - Application trends
  - Result distribution analysis
  - Performance metrics

### Other Controllers
- **RegionController**: Region management CRUD
- **SchoolController**: School management CRUD
- **ExamCentreController**: Exam center management CRUD
- **ExamResultController**: Result management CRUD
- **StudentProfileController**: Student profile management
- **AdminController**: Admin user management

## 🔧 Configuration

### Database Configuration (MySQL)
- **URL**: Clever Cloud MySQL instance
- **Hibernate DDL**: Auto-update schema
- **SQL Logging**: Enabled for debugging
- **Dialect**: MySQL8Dialect

### Redis Configuration
- **URL**: Redis Labs instance
- **Cache Type**: Redis
- **TTL**: 10 minutes default
- **Purpose**: Caching frequently accessed data

### MinIO Configuration
- **URL**: http://100.53.20.30:9000
- **Access Key**: sumit
- **Secret Key**: rastrabhasha
- **Bucket**: student-documents
- **Purpose**: Document storage (photos, certificates, etc.)

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL database
- Redis server (optional, for caching)
- MinIO server (optional, for file storage)

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd webapp
```

2. Configure database and Redis in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/exam_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.data.redis.url=redis://localhost:6379
```

3. Build the project:
```bash
./mvnw clean install
```

4. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### Available Maven Commands

- `./mvnw clean install` - Clean and build the project
- `./mvnw spring-boot:run` - Run the application
- `./mvnw test` - Run tests
- `./mvnw package` - Package as JAR

## 📡 API Endpoints

### Student Endpoints
- `POST /students` - Create student
- `GET /students` - Search students (with pagination)
- `GET /students/{id}` - Get student by ID
- `PUT /students/{id}` - Update student
- `DELETE /students/{id}` - Delete student

### Exam Endpoints
- `POST /exams` - Create exam
- `GET /exams` - Search exams (with pagination)
- `GET /exams/{id}` - Get exam by ID
- `PUT /exams/{id}` - Update exam
- `DELETE /exams/{id}` - Delete exam

### Application Endpoints
- `POST /exam-applications` - Submit application
- `GET /exam-applications` - Search applications (with pagination)
- `GET /exam-applications/{id}` - Get application by ID
- `PUT /exam-applications/{id}` - Update application
- `DELETE /exam-applications/{id}` - Delete application
- `POST /exam-applications/{id}/generate-hall-ticket` - Generate hall ticket

### Result Endpoints
- `POST /exam-results` - Create result
- `GET /exam-results` - Search results (with pagination)
- `GET /exam-results/{id}` - Get result by ID
- `PUT /exam-results/{id}` - Update result
- `DELETE /exam-results/{id}` - Delete result

### School Endpoints
- `POST /schools` - Create school
- `GET /schools` - Search schools (with pagination)
- `GET /schools/{id}` - Get school by ID
- `PUT /schools/{id}` - Update school
- `DELETE /schools/{id}` - Delete school

### Region Endpoints
- `POST /regions` - Create region
- `GET /regions` - Search regions (with pagination)
- `GET /regions/{id}` - Get region by ID
- `PUT /regions/{id}` - Update region
- `DELETE /regions/{id}` - Delete region

### Exam Centre Endpoints
- `POST /exam-centres` - Create exam centre
- `GET /exam-centres` - Search exam centres (with pagination)
- `GET /exam-centres/{id}` - Get exam centre by ID
- `PUT /exam-centres/{id}` - Update exam centre
- `DELETE /exam-centres/{id}` - Delete exam centre

### Analytics Endpoints
- `GET /analytics/summary` - Get dashboard summary
- `GET /analytics/students-by-region` - Get student count by region
- Additional analytics endpoints as needed

## 🔐 Authentication

Currently, the API does not implement authentication. For production, consider adding:
- Spring Security with JWT tokens
- Role-based access control (RBAC)
- OAuth2 integration

## 📊 Pagination

All list endpoints support pagination using Spring Data's `Pageable` interface:

```
GET /students?page=0&size=20&sort=firstName,asc
```

Parameters:
- `page` - Page number (0-indexed)
- `size` - Page size
- `sort` - Sorting criteria (field,direction)

## 🔍 Search & Filtering

Most endpoints support advanced filtering through DTOs:
- StudentFilterDTO
- ExamFilterDTO
- ExamApplicationFilterDTO
- ExamResultFilterDTO

Filters are implemented using JPA Specifications for dynamic query generation.

## 🧪 Testing

The project includes Spring Boot Test framework. Test files are located in:
- `src/test/java/com/rasthrabhasha/`

Run tests:
```bash
./mvnw test
```

## 🐳 Docker Support

A Dockerfile is included for containerization:

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/webapp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Build and run:
```bash
docker build -t mrb-webapp .
docker run -p 8080:8080 mrb-webapp
```

## 📝 Environment Variables

Configure the following in `application.properties`:

```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/exam_management
spring.datasource.username=your_username
spring.datasource.password=your_password

# Redis
spring.data.redis.url=redis://localhost:6379

# MinIO
minio.url=http://localhost:9000
minio.access-key=your_access_key
minio.secret-key=your_secret_key
minio.bucket=student-documents
```

## 🔄 Caching Strategy

Redis caching is configured with:
- **TTL**: 10 minutes default
- **Cache Type**: Redis
- **Purpose**: Cache frequently accessed data (students, exams, schools)

To disable caching, remove `@EnableCaching` annotation or set cache type to none.

## 📦 Dependencies

Key dependencies from `pom.xml`:
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Data Redis
- Spring Boot Starter Cache
- MySQL Connector
- MinIO Java SDK
- Jackson Datatype JSR310 (for Java 8 date/time)

## 🤝 Contributing

This is a demo/prototype project for the Maharashtra Rashtrabhasha Sabha examination system.

## 📄 License

Proprietary - Maharashtra Rashtrabhasha Sabha

## 📞 Support

For technical support or queries, please contact the development team.
