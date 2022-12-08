package com.spartan.get;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SpartanGetRequest {

    String baseUrl = "http://3.85.122.36:8000/";

    /*
    Given Accept Type is application/json
    When user sends a GET request to api/spartans end point
    Then status code must be 200
    Then response Content Type must be application/json
    And response body should include spartan result
     */

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .when().get(baseUrl + "api/spartans");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
    }

    /*
    Given Accept Type is application/json
    When user sends a GET request to api/spartans/3
    Then status code should be 200
    And response Content Type should be application/json
    And json body should contain Fidole
     */

    @DisplayName("GET one spartan and verify")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get(baseUrl + "api/spartans/3");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Fidole"));
    }

    /*
    Given no headers provided
    When users sends GET request to api/hello
    Then response status code should be 200
    And Content Type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
     */

}
