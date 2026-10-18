# Student Management Backend

A Java 21 / Spring Boot REST API for the assignment: Student Management using JWT, Spring Data JPA/Hibernate, MySQL, and Postman.

## Features
- User registration and login
- BCrypt password hashing
- JWT generation and authentication
- JWT-protected student CRUD endpoints
- MySQL persistence through Spring Data JPA
- Bean validation
- Postman collection included

## Requirements
- JDK 21
- MySQL Server
- Apache Maven 3.9+ (or use an IDE with Maven support)
- Postman

## 1. Create the database
In MySQL Workbench, run:
```sql
CREATE DATABASE IF NOT EXISTS student_management;
```
Hibernate creates the `app_users` and `students` tables when the app starts.

## 2. Configure MySQL
Open `src/main/resources/application.properties`. Change the default `change_me` password to your MySQL password, or set environment variables:
- `DB_USERNAME`
- `DB_PASSWORD`
- Optional `DB_URL`
- Optional `JWT_SECRET` (Base64-encoded key, at least 32 bytes before encoding)

Do not commit real passwords or production JWT secrets to GitHub. The sample JWT secret is for local assignment use only; replace it before public deployment.

## 3. Build and run
From the folder containing `pom.xml`:
```powershell
mvn clean package
mvn spring-boot:run
```
The server starts at `http://localhost:8080`.

## 4. API endpoints

| Method | Endpoint | Auth |
|---|---|---|
| POST | `/api/auth/register` | Public |
| POST | `/api/auth/login` | Public |
| POST | `/api/students` | Bearer JWT |
| GET | `/api/students` | Bearer JWT |
| GET | `/api/students/{id}` | Bearer JWT |
| PUT | `/api/students/{id}` | Bearer JWT |
| DELETE | `/api/students/{id}` | Bearer JWT |

### Register example
`POST http://localhost:8080/api/auth/register`
```json
{
  "username": "studentuser",
  "password": "Student@123"
}
```
Password must be at least 8 characters. Successful registration returns a token.

### Login example
`POST http://localhost:8080/api/auth/login`
```json
{
  "username": "studentuser",
  "password": "Student@123"
}
```
Copy the `token` from the response. In Postman, select **Authorization → Bearer Token** and paste it, or import the included collection (it stores the token after registration/login).

### Add student example
`POST http://localhost:8080/api/students`
```json
{
  "name": "Aarav Sharma",
  "email": "aarav@example.com",
  "branch": "Computer Engineering",
  "year": 2
}
```

## 5. Postman
Import `postman/Student-Management-API.postman_collection.json`. Run Register or Login first, then run the student requests. The collection stores the returned token automatically.

## 6. GitHub submission
Include source code, `pom.xml`, `database/student_management.sql`, Postman collection, and README. Do not commit credentials. Suggested repository name: `student-management-backend`.

## Troubleshooting
- `Access denied for user`: check MySQL username/password.
- `Communications link failure`: make sure MySQL Server is running and listening on port 3306.
- `mvn is not recognized`: install Apache Maven and add its `bin` folder to PATH, or build using a Maven-enabled IDE.
- `401 Unauthorized` for student endpoints: register/login and send `Authorization: Bearer <token>`.
