# Employee REST API

A Spring Boot REST API for managing employees and departments.


## Setup

1. Create a MySQL database named `employee_db`
2. Update `application.properties` with your MySQL username and password
3. Run the app via `mvn spring-boot:run`
4. Base URL: `http://localhost:8080`

## Endpoints

### Departments
| Method | URL | Description |
|--------|-----|-------------|
| POST | /departments | Create department |
| GET | /departments | Get all departments |
| GET | /departments/{id} | Get department by id |
| DELETE | /departments/{id} | Delete department |

### Employees
| Method | URL | Description |
|--------|-----|-------------|
| POST | /employees | Create employee |
| GET | /employees | Get all employees |
| GET | /employees/{id} | Get employee by id |
| PUT | /employees/{id} | Update employee |
| DELETE | /employees/{id} | Delete employee |
