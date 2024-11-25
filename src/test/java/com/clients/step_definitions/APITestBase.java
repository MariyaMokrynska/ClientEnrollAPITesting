package com.clients.step_definitions;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class APITestBase {

    static RequestSpecification givenPart = RestAssured.given().log().uri();
    static Response response;
    static JsonPath jp;
    static ValidatableResponse thenPart;
}
