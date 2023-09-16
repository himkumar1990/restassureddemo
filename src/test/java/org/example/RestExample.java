package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestExample {

    public static void main(String[] args) throws IOException {
        RestAssured.baseURI = "http://localhost:8080/examples/webservices/";

        RestAssured.given().sessionId("1234").log().parameters().get("/lotto.json").then().rootPath("lotto").detachRootPath("lotto").body("lotto.lottoId", equalTo(5));

        RestAssured.given().get("/first.json").then().body("$", hasItems(1, 2, 3));
        String session = RestAssured.given().sessionId("1234").get("/first.json").sessionId();
        System.out.println("session " + session);

//        Map params;
//        params = new HashMap<String, String>();
//        params.put("firstName", "himanshu");
//        params.put("lastName", "kumar");
//        String resp = RestAssured.given().params(params).post("/greet.xml").asString();
//        System.out.println(resp);

        String html = getFileContent("First.html");
        XmlPath xmlPath = new XmlPath(XmlPath.CompatibilityMode.HTML, html);
        String title = xmlPath.getString("html.body.p");
        System.out.println(title);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/store.json")
                .then()
                .body("store.book.findAll{it.price > 10}.author", hasItems("Evelyn Waugh"));

    }

    public static String getFileContent(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }


}

