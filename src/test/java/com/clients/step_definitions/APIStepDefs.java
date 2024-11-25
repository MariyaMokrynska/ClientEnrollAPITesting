package com.clients.step_definitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class APIStepDefs extends APITestBase {


    @Given("request accept type is {string}")
    public void request_accept_type_is(String acceptHeader) {
        givenPart.accept(acceptHeader);
    }

    @When("I sent GET request to {string} endpoint")
    public void i_sent_get_request_to_endpoint(String endpoint) {
        response = givenPart.when().get(endpoint);
        thenPart = response.then();
        jp = response.jsonPath();
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int expectedStatusCode) {
        // OPT1
        thenPart.statusCode(expectedStatusCode);

        // OPT2
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
    }

    @Then("response content type is {string}")
    public void response_content_type_is(String expectedContentType) {
        // OPT1
        thenPart.contentType(expectedContentType);

        //OPT2
        String actualContentType = response.contentType();
        Assert.assertEquals(expectedContentType,actualContentType);
    }

    @Then("{string} field value should be not null")
    public void field_value_should_be_not_null(String path) {
        // OPT1
        thenPart.body(path, Matchers.notNullValue());

        // OPT2
        Assert.assertNotNull(jp.getJsonObject(path));

    }

    /**
     *
     * Scenario 2
     */
    @Given("path param {string} is {string}")
    public void path_param_is(String pathParam, String value) {
        givenPart.pathParam(pathParam,value);

    }
    @Then("{string} field should be equal {string}")
    public void field_should_be_equal(String path, String expectedValue) {
        // Get actual value from response body
        String actualValue = jp.getString(path);

        // Optional print statements
        System.out.println("-----------------");
        System.out.println("path = " + path);
        System.out.println("actualValue = " + actualValue);
        System.out.println("expectedValue = " + expectedValue);
        System.out.println("-----------------");

        // Assertions
        Assert.assertEquals(expectedValue,actualValue);
    }
}