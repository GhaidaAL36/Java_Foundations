# Library System

## Run

```bash
mvn spring-boot:run
```

## Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | /library | Get all books |
| POST | /library/books | Add a book |
| DELETE | /library/{title} | Remove a book |
| POST | /library/borrow | Borrow a book |
| PUT | /library | Return a book |


**Get all books**
```bash
curl http://localhost:8080/library
```

**Add a book**
```bash
curl -X POST http://localhost:8080/library/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","author":"Robert Martin","isAvailable":true}'
```

**Delete a book**
```bash
curl -X DELETE http://localhost:8080/library/Clean%20Code
```

**Borrow a book**
```bash
curl -X POST http://localhost:8080/library/borrow \
  -H "Content-Type: application/json" \
  -d '{"book":{"title":"Clean Code","author":"Robert Martin","isAvailable":true},"member":{"name":"Ahmed","email":"ahmed@email.com"}}'
```

**Return a book**
```bash
curl -X PUT http://localhost:8080/library \
  -H "Content-Type: application/json" \
  -d '{"id":"LOAN_ID","book":{"title":"Clean Code","author":"Robert Martin","isAvailable":false},"member":{"name":"Ahmed","email":"ahmed@email.com"},"loanDate":"2026-05-31","returnDate":null}'
```
