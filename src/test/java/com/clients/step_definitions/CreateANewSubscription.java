

package com.clients.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;
public class CreateANewSubscription extends APITestBase{
    private Map<String,Object>requestBody=new HashMap<>();
    @Given("I enter {string}, {string}, {string} and {string} as request body")
    public void iEnterAndAsRequestBody(String customerId, String priceId, String paymentMethod, String promoCode) {
        requestBody = new HashMap<>();
        requestBody.put("customerId",customerId );
        requestBody.put("priceId", priceId);
        requestBody.put("paymentMethod", paymentMethod);
        requestBody.put("promoCode", promoCode);
    }
    @Given("I enter {string}, oneTime{string}, {string} and {string} as request body")
    public void iEnterOneTimeAndAsRequestBody(String customerId, String priceId, String paymentMethod, String promoCode) {
        requestBody = new HashMap<>();
        requestBody.put("customerId",customerId );
        requestBody.put("priceId", priceId);
        requestBody.put("paymentMethod", paymentMethod);
        requestBody.put("promoCode", promoCode);
    }
    @Given("I enter {string}, {string}, {string} and inactive {string} as request body")
    public void iEnterAndInactiveAsRequestBody(String customerId, String priceId, String paymentMethod, String promoCode) {
        requestBody = new HashMap<>();
        requestBody.put("customerId",customerId );
        requestBody.put("priceId", priceId);
        requestBody.put("paymentMethod", paymentMethod);
        requestBody.put("promoCode", promoCode);
    }
    @When("I send a POST request to {string} _endpoint")
    public void sendAPOSTRequest(String endpoint) {
        response = givenPart
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when() .post(endpoint).prettyPeek();
        thenPart =response.then();
        jp=response.jsonPath();
    }


    @And("{string} field value should not be null")
    public void fieldValueShouldNotBeNull(String path) {
        thenPart.body(path, Matchers.notNullValue());

    }
}
