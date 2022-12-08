package com.spartan.get;

import com.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanWithHamcrestMatchers extends SpartanTestBase {

     /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 20,
           "name": "Lothario",
           "gender": "Male",
           "phone": 7551551687
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {
                given().log().all() //-> for print request
                    .accept(ContentType.JSON)
                    .and().pathParam("id", 20)
                .when()
                    .get("/api/spartans/{id}")
                .then()
                    .statusCode(200)
                    .and()
                    .contentType("application/json")
                    .and()
                    .body("id", is(20),
                        "name", equalTo("Lothario"),
                        "gender", is(equalTo("Male")),
                        "phone", equalTo(7551551687l))
                    .log().all(); //->for print response
    }

    @DisplayName("GET spartans/search and chaining together")
    @Test
    public void test2() {
        //along with this statement, I want to save names inside the List<String>

        List<String> names = given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "j",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", greaterThanOrEqualTo(3))
                .extract().response().jsonPath().getList("content.name");

        System.out.println(names);
    }

    @DisplayName("GET spartans/search and chaining together")
    @Test
    public void test3() {

        //save status code

        int statusCode = given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "j",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", greaterThanOrEqualTo(3))
                .extract().response().statusCode();

        System.out.println(statusCode);
    }
}
