@ALSEP4-370
Feature: Mark Payment as Successful via API

  As a user,
  I want to mark a payment as successful via API
  So that the payment status is updated correctly in the system.

  @ALSEP4-370_TC01
  Scenario Outline: User marks payment as successful with valid data
    Given request accept type is "application/json"
    And Request Content Type header is "application/json"
    And the user provides the following request body "<paymentType>" and "<paymentId>" and "<priceId>" and "<customerId>" and "<paymentMethod>"

    When I send a POST request to "/plans/success" endpoint
    Then status code should be 204
    And response body should be empty
    Examples:
      | paymentType | paymentId                   | priceId                        | customerId | paymentMethod |
      | recurring   | pi_3PwrZsJmxFuDfdzn0umZySlv | price_1OXpPoJmxFuDfdznFNJZKs0I | 262        | card          |

  @ALSEP4-370_TC02
  # Scenario: Invalid customerId
  Scenario Outline: User submits an invalid customerId that does not belong to any registered customer
    Given request accept type is "application/json"
    And Request Content Type header is "application/json"
    And the user provides the following request body "<paymentType>" and "<paymentId>" and "<priceId>" and "<customerId>" and "<paymentMethod>"
    When I send a POST request to "/plans/success" endpoint
    Then status code should be 404
    And "error.message" field should be equal "Customer could not be found."
    Examples:
      | paymentType | paymentId                  | priceId                        | customerId | paymentMethod |
      | recurring   | pi_3PwZsJmxFuDfdzn0umZySLv | price_10XpPoJmxFuDfdznFNJZKsOI | 99999      | card          |


  @ALSEP4-370_TC03
  Scenario Outline: User submits mismatching paymentId and priceId
    Given request accept type is "application/json"
    And Request Content Type header is "application/json"
    And the user provides the following request body "<paymentType>" and "<paymentId>" and "<priceId>" and "<customerId>" and "<paymentMethod>"
    When I send a POST request to "/plans/success" endpoint
    Then status code should be 409
    And "error.message" field should contains "Something went wrong: No such price"
    Examples:
      | paymentType   | paymentId         | priceId          | customerId | paymentMethod |
      | recurring     | pi_wrong_payment  | price_wrong_price| 262        | card          |

  @ALSEP4-370_TC04
  # Scenario: Invalid payment method
  Scenario Outline: User submits paymentMethod other than "card"
    Given request accept type is "application/json"
    And Request Content Type header is "application/json"
    And the user provides the following request body "<paymentType>" and "<paymentId>" and "<priceId>" and "<customerId>" and "<paymentMethod>"
    When I send a POST request to "/plans/success" endpoint
    Then status code should be 204
    And response body should be empty
    Examples:
      | paymentType   | paymentId                  | priceId                        | customerId | paymentMethod  |
      | recurring     | pi_3PwrZsJmxFuDfdzn0umZySlv | price_1OXpPoJmxFuDfdznFNJZKs0I | 262        | bankTransfer   |




