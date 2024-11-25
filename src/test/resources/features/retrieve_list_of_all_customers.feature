@ALSEP4-373
Feature: Customer feature endpoint
  @ALSEP4-373_AC01
  Scenario: User cannot retrieve the list of customers without X-API-Key
    Given request accept type is "application/json"
    And I do not provide an X-API-Key
    When I sent GET request to "/customers" endpoint
    Then status code should be 500
    And response content type is "application/json"
    And "error.name" field should be equal "Internal Server Error"
    And "error.message" field should be equal "Something went wrong."
    And "success" field should be equal "false"


  @ALSEP4-373_AC02
  Scenario: User can retrieve the list of customers
    Given request accept type is "application/json"
    And X-API-Key should not be null
    When I sent GET request to "/customers" endpoint
    Then status code should be 200
    And response content type is "application/json"
    And "success" field should be equal "true"



