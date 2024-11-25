package com.clients.step_definitions;

import com.clients.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class Sample {

    //    Scenario: Agreement endpoint
    //    Given request accept type is "application/json"
    //    When I sent GET request to "/products/atf/agreement" endpoint
    //    Then status code should be 200
    //    And response content type is "application/json"
    //    And "data.agreementId" field value should be not null


    @Test
    public void withJunit() {

        JsonPath jp = RestAssured.given().log().uri()
                .accept(ContentType.JSON) // API I need response datatype as application/json
                .when().get(ConfigurationReader.getProperty("baseUri") + "/products/atf/agreement")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.agreementId", Matchers.notNullValue())
                .extract().jsonPath();

        // JSONPATH
        int aggrementID = jp.getInt("data.agreementId");
        Assert.assertNotNull(aggrementID);


    }

    @Test
    public void withCucumber() {

        RequestSpecification givenPart = RestAssured.given().log().uri();
        //    Given request accept type is "application/json"
        givenPart.accept(ContentType.JSON);

        //    When I sent GET request to "/products/atf/agreement" endpoint
        Response response = givenPart.when().get(ConfigurationReader.getProperty("baseUri") + "/products/atf/agreement");
        ValidatableResponse thenPart = response.then();
        JsonPath jp=response.jsonPath();

        //    Then status code should be 200
        thenPart.statusCode(200);

        //    And response content type is "application/json"
        thenPart.contentType(ContentType.JSON);

        //    And "data.agreementId" field value should be not null
         // OPT 1
         thenPart.body("data.agreementId", Matchers.notNullValue());

         // OPT 2
        int aggrementID = jp.getInt("data.agreementId");
        Assert.assertNotNull(aggrementID);

    }
}
