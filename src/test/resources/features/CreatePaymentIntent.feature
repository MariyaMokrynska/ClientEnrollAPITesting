@ALSEP4-374
Feature: Payment Intent Creation

  Scenario: Create a new payment intent resource in Stripe.
    Given User should enter all the fields:
      | customerId    | 262                            |
      | productId     | 1                              |
      | priceId       | price_1OXpPoJmxFuDfdznFNJZKs0I |
      | paymentMethod | card                           |

    And the user selects the productId matching the program information from the GET request to the "products/atf" endpoint
    And the priceId should be associated with the "one_time" payment type from the GET request to the "/products/56/prices" endpoint
    When I send POST request to "/plans/payment-intent" endpoint.
    Then status code should be 200
    And response content type is "application/json"
    And The field "data.paymentIntentId" should not be null and its value should be a string
    And The field "data.paymentSecret" should not be null and its value should be a string
    And The field "data.paymentIntentStatus" should not be null and its value should be a string
    And The field "data.price" should not be null and its value should be an int
    And The field "data.currency" should not be null and its value should be "usd"

