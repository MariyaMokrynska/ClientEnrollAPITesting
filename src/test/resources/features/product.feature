@wip
Feature: Product Feature Endpoint

  Scenario: Agreement endpoint
    Given request accept type is "application/json"
    When I sent GET request to "/products/atf/agreement" endpoint
    Then status code should be 200
    And response content type is "application/json"
    And "data.agreementId" field value should be not null


  Scenario: User can get any product resource by program code "atf".
    Given request accept type is "application/json"
    And path param "lmsProgramCode" is "atf"
    When I sent GET request to "/products/{lmsProgramCode}" endpoint
    Then status code should be 200
    And response content type is "application/json"
    And "data.productId" field value should be not null
    And "data.productId" field should be equal "1"
    And "data.lmsProgramId" field should be equal "56"
    And "data.lmsProgramName" field should be equal "Automated Testing Fundamentals"
    And "data.isForTeens" field should be equal "false"



  Scenario Outline: User can get any product resource by program code "<programType>".
    Given request accept type is "application/json"
    And path param "lmsProgramCode" is "<programType>"
    When I sent GET request to "/products/{lmsProgramCode}" endpoint
    Then status code should be 200
    And response content type is "application/json"
    And "data.productId" field value should be not null
    And "data.productId" field should be equal "<productId>"
    And "data.lmsProgramId" field should be equal "<programId>"
    And "data.lmsProgramName" field should be equal "<programName>"
    And "data.isForTeens" field should be equal "<teensResult>"

    Examples:
      | programType | productId | programId | programName                    | teensResult |
      | atf         | 1         | 56        | Automated Testing Fundamentals | false       |

