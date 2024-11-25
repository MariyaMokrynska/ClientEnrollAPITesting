@ALSEP-364
Feature: Create Purchase Endpoint

  Scenario: User creates a new purchase with valid data
    Given request accept type is "application/json"
    And the request body contains valid customer and product information
    When I send POST request to "/purchases" endpoint
    Then status code should be 200
    And response content type is "application/json"
    And "data.customer.customerId" field value should be not null
    And "data.customer.firstName" field should be equal "Jay"
    And "data.customer.lastName" field should be equal "Leno"
    And "data.customer.email" field should be equal "jayleno@gmail.com"
    And "data.customer.parent.parentId" field value should be not null
    And email should be in proper format

  Scenario: Verify specific fields in the purchase response
    Given request accept type is "application/json"
    And the request body contains valid customer and product information
    When I send POST request to "/purchases" endpoint
    Then status code should be 200
    And response content type is "application/json"
    And "data.customer.customerId" field value should be not null
    And "data.customer.parent.parentId" field value should be not null
    And "success" field should be equal "true"
    And "status" field should be equal "200"
