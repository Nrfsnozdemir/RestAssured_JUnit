package com.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("spartanBaseURI");

        String dbUrl = ConfigurationReader.getProperty("dbUrl");
        String dbUsername = ConfigurationReader.getProperty("dbUsername");
        String dbPassword = ConfigurationReader.getProperty("dbPassword");

        //DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void tearDown(){
        //DBUtils.destroy();
    }
}
