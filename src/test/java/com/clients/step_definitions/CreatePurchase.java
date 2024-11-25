package com.clients.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreatePurchase extends APITestBase {

    private Map<String, Object> requestBody = new HashMap<>();

    @Given("the request body contains valid customer and product information")
    public void setValidRequestBody() {

        Map<String, Object> customerData = new HashMap<>();
        customerData.put("firstName", "Jay");
        customerData.put("lastName", "Leno");
        customerData.put("email", "jayleno@gmail.com");
        customerData.put("phoneNumber", "aabbccddee");


        Map<String, Object> parentData = new HashMap<>();
        parentData.put("firstName", "");
        parentData.put("lastName", "");
        parentData.put("email", "");
        parentData.put("phoneNumber", "");
        customerData.put("parent", parentData);


        Map<String, Object> productData = new HashMap<>();
        productData.put("lmsProgramCode", "taws");
        productData.put("lmsProgramId", 56);
        productData.put("lmsProgramName", "Test Automation with Selenium");


        requestBody.put("customer", customerData);
        requestBody.put("product", productData);
        requestBody.put("referralSource", "");
    }

    @When("I send POST request to {string} endpoint")
    public void sendPostRequest(String endpoint) {

        response = givenPart
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(endpoint);

        System.out.println("Response: " + response.prettyPrint());

        thenPart = response.then();
        jp = response.jsonPath();
    }



    @Then("\"{string}\" field value should be not null")
    public void field_value_should_be_not_null(String path) {

        Object fieldValue = jp.get(path);
        System.out.println("Extracted field value for " + path + ": " + fieldValue);
        Assert.assertNotNull("Field " + path + " should not be null", fieldValue);
    }
    @Then("\"{string}\" field should be equal \"{string}\"")
    public void field_should_be_equal(String path, String expectedValue) {
        String actualValue = jp.getString(path);
        System.out.println("Path = " + path);
        System.out.println("Actual Value = " + actualValue);
        System.out.println("Expected Value = " + expectedValue);
        Assert.assertEquals("Field value mismatch for " + path, expectedValue, actualValue);
    }


    @Then("email should be in proper format")
    public void validateEmailFormat() {

        String email = jp.getString("data.customer.email");
        Assert.assertTrue("Email format is invalid", email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));
    }

    @Then("customerId should not be null")
    public void validateCustomerId() {

        thenPart.body("data.customer.customerId", notNullValue());
    }


    @Then("parentId should not be null")
    public void validateParentId() {

        thenPart.body("data.customer.parent.parentId", notNullValue());
    }
}
