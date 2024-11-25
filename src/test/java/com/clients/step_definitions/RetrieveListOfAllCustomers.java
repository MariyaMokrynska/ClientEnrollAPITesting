package com.clients.step_definitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;

public class RetrieveListOfAllCustomers extends APITestBase {
    @Given("X-API-Key should not be null")
    public void x_api_key_should_not_be_null() {
        String apiKey = System.getenv("X_API_KEY");
        Assert.assertNotNull("X-API-Key cannot be null", apiKey);
        Assert.assertFalse("X-API-Key cannot be empty", apiKey.isEmpty());

        givenPart.header("X-API-Key", apiKey);
    }

    @Given("I do not provide an X-API-Key")
    public void i_do_not_provide_an_x_api_key() {
        // just to clear the givenPart from the previous steps' header values
        givenPart = RestAssured.given();
        givenPart.accept(ContentType.JSON);
    }

}
