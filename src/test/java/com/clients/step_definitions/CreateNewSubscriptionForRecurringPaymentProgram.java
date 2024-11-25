package com.clients.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

public class CreateNewSubscriptionForRecurringPaymentProgram extends APITestBase {
    private Map<String, Object> requestBody = new HashMap<>();

    @Given("request body contains valid information for payment plan {string}, {string}, {string}, {string}")
    public void request_body_contains_valid_information_for_payment_plan(String customerId, String programId, String priceId, String paymentMethod) {
        requestBody.clear();
        requestBody.put("customerId", Integer.parseInt(customerId));
        requestBody.put("programId", Integer.parseInt(programId));
        requestBody.put("priceId", priceId);
        requestBody.put("paymentMethod", paymentMethod);
    }

    @Given("request body contains valid information for subscription {string}, {string}, {string}, {string}")
    public void request_body_contains_valid_information_for_subscription(String customerId, String priceId, String paymentMethod, String promoCode) {
        requestBody.clear();
        requestBody.put("customerId", Integer.parseInt(customerId));
        requestBody.put("priceId", priceId);
        requestBody.put("paymentMethod", paymentMethod);
        requestBody.put("promoCode", promoCode);
    }

    @When("I send POST request to {string} endpoint_")
    public void sendPostRequest(String endpoint) {
        response = givenPart
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint);

        thenPart = response.then();
        jp = response.jsonPath();
    }
}
