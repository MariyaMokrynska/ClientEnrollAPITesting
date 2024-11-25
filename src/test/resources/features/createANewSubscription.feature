
@ALSEP-369
Feature: Create a new subscription in Stripe API

  Scenario Outline: Successfully create a subscription for a registered customer
    Given I enter "<customerId>", "<priceId>", "<paymentMethod>" and "<promoCode>" as request body
    When I send a POST request to "/plans/subscription" _endpoint
    Then status code should be 200
    And response content type is "application/json"
    And "data.subscriptionId" field value should not be null
    And "data.subscriptionKey" field value should not be null
    And "data.priceId" field value should not be null
    Examples:
      | customerId | priceId                        | paymentMethod | promoCode |
      | 262        | price_1OXpPoJmxFuDfdznRZujBIls | card          | off50     |

  Scenario Outline: Fail to create a subscription for a one-time price
    Given I enter "<customerId>", oneTime"<priceId>", "<paymentMethod>" and "<promoCode>" as request body
    When I send a POST request to "/plans/subscription" _endpoint
    Then status code should be 409
    And "error.message" field should be equal "Subscriptions cannot be created for one_time prices."
    Examples:
      | customerId | priceId                        | paymentMethod | promoCode |
      | 262        | price_1OXpPoJmxFuDfdznFNJZKs0I | card          | off50     |

  Scenario Outline: Fail to create a subscription with an inactive promo code
    Given I enter "<customerId>", "<priceId>", "<paymentMethod>" and inactive "<promoCode>" as request body
    When I send a POST request to "/plans/subscription" _endpoint
    Then status code should be 409
    And "error.message" field should be equal "Promotion code is no longer active."
    Examples:
      | customerId | priceId                        | paymentMethod | promoCode |
      | 262        | price_1OXpPoJmxFuDfdznRZujBIls | card          | exp50     |
