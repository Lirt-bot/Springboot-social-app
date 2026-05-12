# Spring Boot REST API

A RESTful API built with Spring Boot for managing users and posts. Features JWT-based authentication, role-based access control, and full CRUD operations. API documentation is available via Swagger UI.

---

## Features

- User registration and JWT login
- Secured endpoints via Spring Security
- Role-based access control (USER / ADMIN)
- Create, read, update, and delete posts
- View a user's posts by user ID
- Authenticated users can view their own profile (`/users/me`)
- Global exception handling
- OpenAPI / Swagger UI documentation

---

## Tech Stack

- **Java** with **Spring Boot**
- **Spring Security** — authentication & authorization
- **JWT** — stateless token-based auth (`TokenService`, `KeyGenerator`)
- **Spring Data JPA** — database access
- **Jakarta Validation** — request body validation
- **OpenAPI / Swagger** — auto-generated API docs (`OpenAPIConfig`)
- **DTO pattern** — clean separation of API and internal models

---

## API Endpoints

### Authentication

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| `POST` | `/auth/login` | Public | Log in and receive a JWT token |

### Users

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| `POST` | `/users` | Public | Register a new user |
| `GET` | `/users/me` | Authenticated | Get the currently logged-in user |
| `GET` | `/users` | ADMIN only | Get all users |
| `GET` | `/users/{id}/posts` | Public | Get a user with their posts |

### Posts

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| `GET` | `/posts` | Public | Get all posts (newest first) |
| `GET` | `/posts/{id}` | Public | Get a specific post |
| `POST` | `/posts` | Authenticated | Create a new post |
| `PUT` | `/posts/{id}` | Authenticated | Update your own post |
| `DELETE` | `/posts/{id}` | Authenticated | Delete your own post |

### Admin

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| TBD | `/admin/...` | ADMIN only | Admin-specific operations |

---

## Authentication

This API uses **JWT (JSON Web Tokens)** for authentication.

1. Register a user via `POST /users`
2. Log in via `POST /auth/login` with your credentials
3. Copy the token from the response
4. Include it in subsequent requests as a header:

```
Authorization: Bearer <your-token>
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven

### Run locally

```bash
# Clone the repository
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name

# Build and run
./mvnw spring-boot:run
```

The API will start on `http://localhost:8080`.

### Swagger UI

Once running, visit:

```
http://localhost:8080/swagger-ui.html
```

---

## Project Structure

```
src/main/java/se/jensen/linus/springboot1/
├── config/
│   └── OpenAPIConfig.java
├── controller/
│   ├── AdminController.java
│   ├── AuthController.java
│   ├── HelloController.java
│   ├── PostController.java
│   └── UserController.java
├── DTO/
│   ├── LoginRequestDTO.java
│   ├── LoginResponseDTO.java
│   ├── PostRequestDTO.java
│   ├── PostResponseDTO.java
│   ├── UserRequestDTO.java
│   ├── UserResponseDTO.java
│   ├── UserResponseDTOBuilder.java
│   └── UserWithPostsResponseDTO.java
├── exceptions/
│   └── GlobalExceptionHandler.java
├── mapper/
│   ├── PostMapper.java
│   └── UserMapper.java
├── Model/
│   ├── Post.java
│   ├── User.java
│   └── UserBuilder.java
├── repository/
│   ├── PostRepository.java
│   └── UserRepository.java
├── security/
│   ├── KeyGenerator.java
│   ├── MyUserDetails.java
│   └── SecurityConfig.java
├── service/
│   ├── MyUserDetailsService.java
│   ├── PostService.java
│   ├── TokenService.java
│   └── UserService.java
└── SpringBoot1Application.java
```

---

## Author

Linus — [Jensen Education]
