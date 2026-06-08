# Task List 

REST API for managing tasks, built with Spring Boot and MySQL.

## Running with Docker

```bash
docker-compose up --build
```

This starts:
- MySQL on port 3307
- RabbitMQ on port 5672 (UI: http://localhost:15672)
- Kafka on port 9092
- Keycloak on port 8181 (http://localhost:8181)
- App on port 8080

## Keycloak Setup

1. Go to http://localhost:8181 and login with `admin` / `admin`
2. Create realm `taskList`
3. Create client `tasklist-client` with **Direct access grants** enabled
4. Go to **Credentials** tab and copy the client secret
5. Create roles `user` and `admin` under **Realm roles**
6. Create users and assign roles under **Users**
7. Go to **Realm settings** → **User profile** and turn off required for `firstName`, `lastName`, `email`

## Running Locally

1. Create a MySQL database called `tasklist`
2. Copy `application.properties.example` to `application.properties` and fill in your DB credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tasklist
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. Run the app with `mvn spring-boot:run`

## Endpoints

| Method | URL | Description | Role Required |
|--------|-----|-------------|---------------|
| GET | `/tasks` | Get all tasks | user, admin |
| GET | `/tasks/{id}` | Get task by id | user, admin |
| POST | `/tasks` | Create a task | user, admin |
| PUT | `/tasks/{id}` | Update a task | user, admin |
| DELETE | `/tasks/{id}` | Delete a task | admin only |

## Security

All endpoints require a Bearer token. Get one from Keycloak:

```bash
curl -X POST http://localhost:8181/realms/taskList/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=tasklist-client" \
  -d "client_secret=YOUR_CLIENT_SECRET" \
  -d "username=YOUR_USERNAME" \
  -d "password=YOUR_PASSWORD"
```

Then use the token in your requests:

```bash
curl http://localhost:8080/tasks \
  -H "Authorization: Bearer YOUR_TOKEN"
```

| Role | Permissions |
|------|-------------|
| `user` | GET, POST, PUT |
| `admin` | GET, POST, PUT, DELETE |


## Running Tests

```bash
mvn test
```
