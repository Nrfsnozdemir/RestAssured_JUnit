package com.spartan.get;

import com.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithContains extends SpartanTestBase {

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
                .when().get("/api/spartans");

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

    @DisplayName("GET request to /api/spartans/3")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans/3");

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

    @DisplayName("GET request to /api/hello")
    @Test
    public void test3() {
        Response response = when().get("/api/hello");

        assertEquals(200, response.statusCode());
        assertEquals("text/plain;charset=UTF-8", response.contentType());
        assertTrue(response.headers().hasHeaderWithName("date"));
        assertEquals("17", response.header("Content-Length"));
        assertEquals("Hello from Sparta", response.body().asString());
    }

    /*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */

    @DisplayName("GET request to /api/spartans/{id}")
    @Test
    public void test4() {
        Response response = given()
                .accept(ContentType.JSON).and().pathParam("id", 5)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Blythe"));

    }

    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @DisplayName("GET request to /api/spartans/{id} Negative Test")
    @Test
    public void test5() {
        Response response = given()
                .accept(ContentType.JSON).and().pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));

    }

    /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test6() {
        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().queryParams("gender", "Female")
                .and().queryParams("nameContains", "e")
                .when()
                .get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test7() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().log().all()
                                    .accept(ContentType.JSON)
                                    .and().queryParams(queryMap)
                            .when()
                                    .get("/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();
    }

}
