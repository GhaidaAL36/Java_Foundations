# Task List API

A simple REST API for managing tasks, built with Spring Boot and MySQL.

## Setup

1. Create a MySQL database called `tasklist`
2. Copy `application.properties.example` to `application.properties` and fill in your DB credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tasklist
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. Run the app from IntelliJ or with `mvn spring-boot:run`

## Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/tasks` | Get all tasks |
| GET | `/tasks/{id}` | Get task by id |
| POST | `/tasks` | Create a task |
| PUT | `/tasks/{id}` | Update a task |
| DELETE | `/tasks/{id}` | Delete a task |

## Curl commands

### create task

```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My first task",
    "description": "Testing the API",
    "status": "TODO",
    "assignee": "Ghaida"
  }'
```


### GET all tasks


```bash
curl http://localhost:8080/tasks
```


### GET task by id


```bash
curl http://localhost:8080/tasks/task_id
```


### PUT - update task


```bash
curl -X PUT http://localhost:8080/tasks/52b0c8fb-5cfc-4e78-a6e7-28b648d1036a \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated task",
    "description": "Testing update",
    "status": "IN_PROGRESS",
    "assignee": "Ghaida"
  }'
```


### DELETE


```bash
curl -X DELETE http://localhost:8080/tasks/task_id
```

## Running Tests

```bash
mvn test
```
