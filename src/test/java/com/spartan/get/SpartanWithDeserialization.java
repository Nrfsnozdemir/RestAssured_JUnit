package com.spartan.get;

import com.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanWithDeserialization extends SpartanTestBase {

        @DisplayName("GET one Spartan and deserialize to Map")
        @Test
        public void oneSpartanToMap(){
        Response response = given().accept(ContentType.JSON)
                                .and()
                                    .pathParam("id", 15)
                                .when()
                                    .get("/api/spartans/{id}")
                                .then()
                                    .statusCode(200).extract().response();

        Map<String,Object> jsonMap = response.as(Map.class);
        System.out.println(jsonMap);
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));
        }

        @DisplayName("GET all spartans to JAVA data structure")
        @Test
        public void getAllSpartan(){

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        List<Map<String,Object>> jsonList = response.as(List.class);
        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

        Map<String,Object> thirdSpartan = jsonList.get(2);
        System.out.println("thirdSpartan = " + thirdSpartan);
        }
}
