package com.ghaida.service;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
public class EmployeeResourceTest {
    @Test
    void shouldGetAllEmployees() {
        given()
                .when().get("/employees")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(0));
    }

    @Test
    void shouldCreateEmployee() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"departmentName\": \"Engineering\"}")
                .when().post("/departments")
                .then()
                .statusCode(201);

        int departmentId = given()
                .when().get("/departments")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("[0].departmentId");

        given()
                .contentType(ContentType.JSON)
                .body(String.format("""
                        {
                            "name": "Ghaida",
                            "email": "ghaida@gmail.com",
                            "phone": "0501234567",
                            "position": "Developer",
                            "departmentId": %d
                        }
                        """, departmentId))
                .when().post("/employees")
                .then()
                .statusCode(201)
                .body("name", equalTo("Ghaida"))
                .body("email", equalTo("ghaida@gmail.com"));
    }

    @Test
    void shouldReturn404WhenEmployeeNotFound() {
        given()
                .when().get("/employees/9999")
                .then()
                .statusCode(404);
    }

    @Test
    void shouldDeleteEmployee() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"departmentName\": \"HR\"}")
                .when().post("/departments")
                .then()
                .statusCode(201);

        int departmentId = given()
                .when().get("/departments")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("[0].departmentId");

        int employeeId = given()
                .contentType(ContentType.JSON)
                .body(String.format("""
                        {
                            "name": "Ghaida",
                            "email": "ghaida@gmail.com",
                            "phone": "0501234567",
                            "position": "Developer",
                            "departmentId": %d
                        }
                        """, departmentId))
                .when().post("/employees")
                .then()
                .statusCode(201)
                .extract().jsonPath().getInt("id");

        given()
                .when().delete("/employees/" + employeeId)
                .then()
                .statusCode(204);

        given()
                .when().get("/employees/" + employeeId)
                .then()
                .statusCode(404);
    }
}
