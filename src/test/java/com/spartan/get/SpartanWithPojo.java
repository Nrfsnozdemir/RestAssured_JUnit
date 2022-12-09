package com.spartan.get;

import com.spartan.pojo.Search;
import com.spartan.pojo.Spartan;
import com.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanWithPojo extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();

        //Deserialize --> JSON to POJO (java custom class)
            //1.as() method uses jackson to de serialize(converting JSON to Java class)
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());

            //2.using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();
        Spartan s15 = jsonPath.getObject("", Spartan.class);
        System.out.println(s15);
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getPhone() = " + s15.getPhone());
    }

    @DisplayName("Get one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo() {
        ///spartans/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type Spartan POJO
        JsonPath jsonPath = given().accept(ContentType.JSON)
                                .and().queryParams("nameContains", "a",
                                "gender", "Male")
                            .when()
                                .get("/api/spartans/search").
                            then().statusCode(200).
                                    extract().response().jsonPath();

        Spartan firstSpartan = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("firstSpartan = " + firstSpartan);
    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);

        System.out.println(searchResult.getContent().get(0).getName());
    }

    @DisplayName("GET  /spartans/search and save as List<Spartan>")
    @Test
    public void test4(){
        List<Spartan> spartanList = given()
                .accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "a",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("items", Spartan.class); //alternative way->without create the Search class

        System.out.println(spartanList.get(1).getName());

    }
}
