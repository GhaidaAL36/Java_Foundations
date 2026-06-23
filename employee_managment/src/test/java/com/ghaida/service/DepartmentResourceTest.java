package com.ghaida.service;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
public class DepartmentResourceTest {

    @Test
    void shouldGetAllDepartments() {
        given()
                .when().get("/departments")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(0));
    }

    @Test
    void shouldCreateDepartment() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"departmentName\": \"Engineering\"}")
                .when().post("/departments")
                .then()
                .statusCode(201)
                .body("departmentName", equalTo("Engineering"));
    }

    @Test
    void shouldReturn404WhenDepartmentNotFound() {
        given()
                .when().get("/departments/9999")
                .then()
                .statusCode(404);
    }
}
