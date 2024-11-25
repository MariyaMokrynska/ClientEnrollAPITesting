@ALSEP4-372
Feature: Subscription feature endpoint

  @ALSEP4-372_AC01
  Scenario Outline: User can create a new payment plan
    Given request body contains valid information for payment plan "<customerId>", "<programId>", "<priceId>", "<paymentMethod>"
    When I send POST request to "/plans" endpoint_
    Then status code should be 200
    And response content type is "application/json"
    And "data.paymentId" field value should be not null
    And "data.paymentKey" field value should be not null
    And "data.priceId" field should be equal "<priceId>"
    And "data.type" field should be equal "recurring"

    Examples:
      | customerId | programId | priceId                        | paymentMethod |
      | 266        | 11        | price_1OldasJmxFuDfdzn0IcbtmL5 | card          |

  @ALSEP4-372_AC02
  Scenario Outline: User can create a new subscription for a recurring payment program
    Given request body contains valid information for subscription "<customerId>", "<priceId>", "<paymentMethod>", "<promoCode>"
    When I send POST request to "/plans/subscription" endpoint_
    Then status code should be 200
    And response content type is "application/json"
    And "data.subscriptionId" field value should be not null
    And "data.subscriptionKey" field value should be not null
    And "data.priceId" field value should be not null
    And "success" field should be equal "true"

    Examples:
      | customerId | priceId                        | paymentMethod | promoCode |
      | 266        | price_1OldasJmxFuDfdzn0IcbtmL5 | card          | off50     |




