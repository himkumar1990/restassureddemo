package org.example;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class RedirectionTests {
    static RestAssuredConfig  config = RestAssured.config.redirect(redirectConfig().followRedirects(false));

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://api.github.com";

    }

    @Test
    public void testRedirection(){
        Response response = RestAssured.given().config(config).get("/repos/twitter/bootstrap");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 301);
        JsonPath jsonPath = response.getBody().jsonPath();
        String location = response.getHeader("location");
        RestAssured.get(location).then().assertThat().statusCode(200);
        System.out.println(location);
    }

}
