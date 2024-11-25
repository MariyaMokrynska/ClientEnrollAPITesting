package com.clients.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import java.util.Map;

public class API_UserCanRetrieveReviewForAPlanResource extends APITestBase {

    Map<String, Object> map;

    @Given("User enters all the fields of related customer")
    public void user_enters_all_the_fields_of_related_customer(Map<String, Object> map) {

        this.map = map;

        String expectedPriceId = givenPart
                .contentType(ContentType.JSON)
                .get("/products/56/prices")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getString("data.prices[0].priceId");

        Assert.assertEquals(expectedPriceId, map.get("priceId"));
    }

    @Given("Send Post request to {string} endpoint")
    public void send_post_request_to_endpoint(String string) {

        response = givenPart.contentType(ContentType.JSON)
                .body(map)
                .post("/plans/review");

        thenPart = response.then();
        jp = response.jsonPath();
    }


    @Then("The value of the paymentType should be the same paymentType of the related priceId")
    public void the_value_of_the_payment_type_should_be_the_same_payment_type_of_the_related_price_id() {

        String expectedPaymentType = givenPart
                .contentType(ContentType.JSON)
                .get("/products/56/prices")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getString("data.prices[0].receipt.paymentType");

        String actualPaymentType = jp.getString("data.paymentType");

        Assert.assertEquals(expectedPaymentType, actualPaymentType);
    }

    @Then("The value of the currency should be {string}")
    public void the_value_of_the_currency_should_be(String expectedCurrency) {

        String actualCurrency = jp.getString("data.paymentSummary.currency");

        Assert.assertEquals(expectedCurrency, actualCurrency);
    }
}
