package com.clients.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;

public class mark_payment_successful extends APITestBase{


    @When("I send a POST request to {string} endpoint")
    public void iSendAPOSTRequestToEndpoint (String endpoint) {
        response = givenPart.when().post(endpoint).prettyPeek();
        thenPart = response.then();
        jp = response.jsonPath();
    }


//    @And("Request Content Type header is {string}")
//    public void request_content_type_header_is (String contentType) {
//        givenPart.contentType(contentType);
//    }


    @And("the user provides the following request body {string} and {string} and {string} and {string} and {string}")
    public void theUserProvidesTheFollowingRequestBodyAndAndAndAnd(String paymentType, String paymentId, String priceId, String customerId, String paymentMethod) {
        Map<String ,Object > requestBody = new HashMap<>();

        //this is an integer number, I need to create "int" and Parse it later.
        int customerID= Integer.parseInt(customerId);
        requestBody.put("paymentType", paymentType);
        requestBody.put("paymentId", paymentId);
        requestBody.put("priceId", priceId);
        requestBody.put("customerId",customerId);
        requestBody.put("paymentMethod",paymentMethod);
        requestBody.put("isSuccessful",true);
        givenPart.body(requestBody);
    }

    @And("response body should be empty")
    public void responseBodyShouldBeEmpty() {
        thenPart.body(Matchers.isEmptyString());
    }

    @And("{string} field should contains {string}")
    public void fieldShouldContains(String path, String expectedMessage) {
        thenPart.body(path, Matchers.containsString(expectedMessage));
    }
}

