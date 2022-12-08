package com.spartan.get;

import com.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithPath extends SpartanTestBase {

    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @DisplayName("GET one spartan with Path Method")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                                    .and().pathParam("id", 10)
                            .when()
                                    .get("/api/spartans/{id}");

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936L, phone);
    }

    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                            .when().get("/api/spartans");

        int firstId = response.path("id[0]");
        String name = response.path("name[0]");
        String lastFirstName = response.path("name[-1]");

        //save names inside the list of string
        List<String> names = response.path("name");
        System.out.println(names);
        //print each name one by one
        for (String each : names) {
            System.out.println(each);
        }

    }
}
