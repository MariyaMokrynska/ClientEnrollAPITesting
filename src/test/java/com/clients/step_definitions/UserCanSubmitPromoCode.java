package com.clients.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

public class UserCanSubmitPromoCode extends APITestBase {

    @Given("Request Content Type header is {string}")
    public void request_content_type_header_is(String contentType) {
        givenPart.contentType(contentType);

    }


    @When("I sent Post request to {string} endpoint")
    public void i_sent_post_request_to_endpoint(String endpoint) {
        response = givenPart.when().post(endpoint).prettyPeek();
        thenPart = response.then();
        jp = response.jsonPath();

    }

    @And("I should enter {string} and {string} and {string} as request body")
    public void iShouldEnterAndAndAsRequestBody(String priceId, String productId, String promoCode) {
        Map<String, String> data = new HashMap<>();
        data.put("priceId", priceId);
        data.put("productId", productId);
        data.put("promoCode", promoCode);

        givenPart.body(data);
    }

    @And("the value of the {string} field should be an int value")
    public void theValueOfTheFieldShouldBeAnIntValue(String price) {
        thenPart.body(price, isA(Integer.class))
                .body(price, equalTo(500));
    }
}
