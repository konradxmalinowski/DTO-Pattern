## DTO Pattern (Spring Boot) ✨

Build a clean REST API that demonstrates the Data Transfer Object (DTO) pattern with Spring Boot, Spring Data JPA, and MySQL. The project showcases mapping from JPA entities to lightweight, API-safe DTOs. 🚀

### What’s inside

- **Spring Boot 3.5.x** for application scaffolding
- **Spring Web** for REST controllers
- **Spring Data JPA** for persistence
- **MySQL** runtime driver
- **DTOs (Java records)** to decouple API models from entities

### Why DTOs? 🤔

DTOs help you:

- **Hide sensitive fields** (e.g., passwords) from API responses
- **Stabilize contracts** even when your database model changes
- **Reduce payload size** and prevent over-posting/under-posting issues

---

### Project structure

```text
src/main/java/org/example/dtopattern/
  ├─ DtoPatternApplication.java
  └─ User/
     ├─ User.java              # JPA entity (users table)
     ├─ UserRecord.java        # DTO record exposed by the API
     ├─ UserRepository.java    # Spring Data JPA repository
     ├─ UserService.java       # Business logic + mapping Entity → DTO
     └─ UserController.java    # REST endpoints

src/main/resources/
  └─ application.properties    # DB config, JPA settings
```

---

### Endpoints 🧭

Base URL: `/api/v1/users`

| Method | Path    | Description    | Response                                   |
| ------ | ------- | -------------- | ------------------------------------------ |
| GET    | `/`     | List users     | `List<UserRecord>` (200) or 204 when empty |
| GET    | `/{id}` | Get user by id | `UserRecord` (200) or 404 if not found     |

`UserRecord` fields returned:

- `id`
- `username`
- `email`

Note: The entity contains a `password` field which is intentionally omitted in the DTO. 🔐

---

### Prerequisites ✅

- Java 24 (configured via `pom.xml` property `java.version`)
- Maven Wrapper (provided: `mvnw`, `mvnw.cmd`)
- MySQL running locally

---

### Configuration ⚙️

Default DB config is in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring-dto?useSSL=false
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

- Create the database schema first:

```sql
CREATE DATABASE IF NOT EXISTS `spring-dto` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

- Update `spring.datasource.username` and `spring.datasource.password` as needed.
- `ddl-auto=create-drop` is convenient for demos/tests. For real projects, prefer `validate` or `update` (with migrations).

---

### Run locally 🏃

On Windows PowerShell or CMD:

```bash
./mvnw.cmd spring-boot:run
```

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

The app starts on `http://localhost:8080`.

---

### Quick test with curl 🔎

```bash
# List users (expected 200 OK or 204 No Content)
curl -i http://localhost:8080/api/v1/users

# Get user by id (replace 1 with an existing id)
curl -i http://localhost:8080/api/v1/users/1
```

---

### How mapping works 🧩

- `UserService#getUsers()` fetches `List<User>` and maps each to `UserRecord` via stream mapping.
- `UserService#getUserById(int id)` fetches an `Optional<User>`, then maps to `UserRecord` if present.
- `UserController` returns appropriate HTTP status codes: 200/204 for list, 200/404 for single, 500 for unexpected errors.

---

### Build a jar 📦

```bash
./mvnw -DskipTests package
# resulting jar under target/
```

Run the jar:

```bash
java -jar target/DTO-Pattern-0.0.1-SNAPSHOT.jar
```

---

### Notes & tips 📝

- This project uses Lombok. Make sure your IDE has Lombok annotation processing enabled.
- For production, externalize config (e.g., environment variables) and never commit real credentials.
- Consider adding validation, request DTOs, and error handling (problem+json) as next steps.
