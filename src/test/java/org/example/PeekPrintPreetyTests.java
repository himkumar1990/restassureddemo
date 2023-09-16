package org.example;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PeekPrintPreetyTests {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void testPeek(){
        RestAssured.get().peek();
    }
    @Test
    public void testPrintPreety(){
        RestAssured.get().prettyPrint();
    }

    @Test
    public void testPeekPreety(){
        RestAssured.get().prettyPeek();
    }
}
