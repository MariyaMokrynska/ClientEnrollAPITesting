@ALSEP4-367
Feature: Submit promo code / Validate in Stripe

  @ALSEP4-367_TC01
  Scenario Outline: Verify the the promo code if it is valid
    Given request accept type is "application/json"
    And Request Content Type header is "application/json"
    And I should enter "<priceId>" and "<productId>" and "<promoCode>" as request body
    When I sent Post request to "/plans/promo-code" endpoint
    Then status code should be 200
    And response content type is "application/json"
    And "data.promoCode" field should be equal "<promoCode>"
    And "data.isValid" field should be equal "<isValid>"
    And "data.message" field should be equal "<message>"

    Examples:
      | priceId                        | productId           | promoCode | isValid | message                     |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PapJzI7O1qpqU8 | off50     | true    | Valid Coupon!               |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PapJzI7O1qpqU8 | exp50     | false   | Coupon is no longer active. |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PMYWvWHeM3LeY9 | off50     | true    | Valid Coupon!               |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PMYWvWHeM3LeY9 | exp50     | false   | Coupon is no longer active. |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PapFaO5yScBged | off50     | true    | Valid Coupon!               |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PapFaO5yScBged | exp50     | false   | Coupon is no longer active. |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PapHKIwdh1poXM | off50     | true    | Valid Coupon!               |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PapHKIwdh1poXM | exp50     | false   | Coupon is no longer active. |

  @ALSEP4-367_TC02
  Scenario Outline: Verify the value of the price should be an int value and the value of the currency should be “usd”
                    if the promo Code is "off50"
    Given request accept type is "application/json"
    And Request Content Type header is "application/json"
    And I should enter "<priceId>" and "<productId>" and "<promoCode>" as request body
    When I sent Post request to "/plans/promo-code" endpoint
    Then status code should be 200
    And response content type is "application/json"
    And the value of the "data.receipt.paymentSummary.basePrice" field should be an int value
    And "data.receipt.paymentSummary.currency" field should be equal "usd"

    Examples:
      | priceId                        | productId           | promoCode |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PapJzI7O1qpqU8 | off50     |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PMYWvWHeM3LeY9 | off50     |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PapFaO5yScBged | off50     |
      | price_1OXpPoJmxFuDfdznFNJZKs0I | prod_PapHKIwdh1poXM | off50     |
