package com.clients.step_definitions;

import com.clients.pojos.PaymentIntentRequest;
import com.clients.utilities.PriceUtils;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CreatePaymentIntent extends APITestBase {

    private Map<String, Object> requestBody = new HashMap<>();

    @Given("User should enter all the fields:")
    public void user_should_enter_all_the_fields(Map<String, String> map) {
        PaymentIntentRequest paymentIntent = new PaymentIntentRequest(
                Integer.parseInt(map.get("customerId")),
                Integer.parseInt(map.get("productId")),
                map.get("priceId"),
                map.get("paymentMethod"));

        requestBody.put("customerId", paymentIntent.getCustomerId());
        requestBody.put("productId", paymentIntent.getProductId());
        requestBody.put("priceId", paymentIntent.getPriceId());
        requestBody.put("paymentMethod", paymentIntent.getPaymentMethod());

        System.out.println("Sending request with the following values:");
        System.out.println("Customer ID: " + map.get("customerId"));
        System.out.println("Product ID: " + map.get("productId"));
        System.out.println("Price ID: " + map.get("priceId"));
        System.out.println("Payment Method: " + map.get("paymentMethod"));
    }

    @And("the user selects the productId matching the program information from the GET request to the {string} endpoint")
    public void the_user_selects_the_product_id_matching_the_program_information_from_the_get_request_to_the_endpoint(String endpoint) {
        System.out.println("Sending GET request to products endpoint: " + endpoint);

        response = givenPart.when().get(endpoint);
        thenPart = response.then().log().body();

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + " ms");
    }

    @And("the priceId should be associated with the {string} payment type from the GET request to the {string} endpoint")
    public void the_price_id_should_be_associated_with_the_payment_type_from_the_get_request_to_the_endpoint(String paymentType, String endpoint) {
        System.out.println("Sending GET request to prices endpoint: " + endpoint);

        response = givenPart.when().get(endpoint);
        thenPart = response.then().log().body();

        List<Map<String, Object>> prices = response.jsonPath().getList("data.prices");

        if (PriceUtils.arePricesValid(prices)) {
            String selectedPriceId = PriceUtils.getPriceIdForPaymentType(prices, paymentType);
            PriceUtils.logPriceId(selectedPriceId, paymentType);
        } else {
            System.out.println("No prices found in the response.");
        }

        PriceUtils.logResponseDetails(response);
    }

    @When("I send POST request to {string} endpoint.")
    public void i_send_post_request_to_endpoint(String endpoint) {
        System.out.println("Sending POST request to endpoint: " + endpoint);

        PaymentIntentRequest requestPayload = new PaymentIntentRequest(
                262, 1, "price_1OXpPoJmxFuDfdznFNJZKs0I", "card");

        Gson gson = new Gson();
        String requestBody = gson.toJson(requestPayload);

        System.out.println("Request Body: " + requestBody);

        response = givenPart
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(endpoint);

        jp = response.jsonPath();
        response.then().log().body();
        thenPart = response.then();
    }


    @Then("The field {string} should not be null and its value should be a string")
    public void the_field_should_not_be_null_and_its_value_should_be_a_string(String fieldPath) {
        System.out.println("Executing check for field: " + fieldPath);

        thenPart.body(fieldPath, Matchers.notNullValue());
        String fieldValue = jp.getString(fieldPath);

        System.out.println("Checking field: " + fieldPath + " | Value: " + fieldValue);

        assertNotNull("Field value is null", fieldValue);
    }

    @And("The field {string} should not be null and its value should be an int")
    public void the_field_should_not_be_null_and_its_value_should_be_an_int(String fieldPath) {
        thenPart.body(fieldPath, Matchers.notNullValue());
        int fieldValue = jp.getInt(fieldPath);

        System.out.println("Checking field: " + fieldPath + " | Value (int): " + fieldValue);

        assertTrue("Field value is not an integer", fieldValue > 0);
    }

    @And("The field {string} should not be null and its value should be {string}")
    public void the_field_should_not_be_null_and_its_value_should_be(String fieldPath, String expectedValue) {
        thenPart.body(fieldPath, Matchers.notNullValue());
        String actualValue = jp.getString(fieldPath);

        System.out.println("Checking field: " + fieldPath);
        System.out.println("Expected value: " + expectedValue);
        System.out.println("Actual value: " + actualValue);

        assertEquals("Field value does not match", expectedValue, actualValue);
    }


}
