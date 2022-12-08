package com.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HrTestBase {

    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("hrBaseURI");
    }
}
