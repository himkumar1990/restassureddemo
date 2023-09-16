package org.example;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;

import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class APITestExamples2 {

    @Test
    public void schemaValidator(){
        baseURI = "http://localhost:8080/examples/webservices/";
        given().contentType(ContentType.JSON
        ).when().get("/employees.json").then().assertThat().body(matchesJsonSchemaInClasspath("employee.json"));
    }

}
