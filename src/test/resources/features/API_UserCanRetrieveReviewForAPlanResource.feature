@ALSEP4-368
Feature: API-User can retrieve review for a plan resource

  Scenario: API-User can retrieve review for a plan resource
    Given User enters all the fields of related customer
      | key           | value                          |
      | customerId    | 262                            |
      | priceId       | price_1OXpPoJmxFuDfdznFNJZKs0I |
      | paymentId     | pi_3PwrZsJmxFuDfdzn0umZySlv    |
      | paymentMethod | card                           |
      | coupon        | off50                          |
    And Send Post request to "/plans/review" endpoint
    Then status code should be 200
    And response content type is "application/json"
    And The value of the paymentType should be the same paymentType of the related priceId
    And The value of the currency should be "usd"

