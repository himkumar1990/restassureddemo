package org.example;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PathPramsExamples {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void pathParamTest() {
        RestAssured.given().pathParam("user", "himkumar1990").
                get("users/{user}").
                           then().assertThat().statusCode(200);
    }
}
