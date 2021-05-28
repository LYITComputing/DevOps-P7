Feature: TopUp Account
  This feature describes various scenarios for users adding funds to their revolut account(s)

  #As a user, I can topup my Revolut account using my debit card

  Scenario: Add money to Revolut account using debit card
    Given Danny has 10 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 110


  Scenario: Add money to Revolut account using bank account
    Given Danny has 20 euro in his euro Revolut account
    And Danny selects 230 euro as the topUp amount
    And  Danny selects his BankAccount as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 250

  #To implement this scenario you will need to use data tables
    # https://cucumber.io/docs/cucumber/api/
  Scenario Outline: Add various amounts to Revolut account
    Given Danny has a starting balance of <startBalance>
    And Danny selects his DebitCard as his topUp method
    When Danny now tops up by <topUpAmount>
    Then The balance in his euro account should be <newBalance>
    Examples:
      | startBalance| topUpAmount | newBalance  |
      | 0           | 100         | 100         |
      | 14          | 20          | 34          |
      | 23          | 30          | 53          |
      | 50          | 50          | 100         |
      | 13          | 30          | 43          |
      | -10         | 20          | 10          |

  Rule: The account balance shouldn't change if the topup payment request is rejected by the payment service

    #The scenarios below will need a payment service that accepts or rejects a request to add funds
    Scenario: Payment service rejects the request
      Given Danny has 20 euro in his euro Revolut account
      And Danny selects -10 euro as the topUp amount
      And  Danny selects his LoanAccount as his topUp method
    #And  Danny selects his BankAccount as his topUp method
      When Danny tops up
      Then The new balance of his euro account should now be 20

    Scenario: Payment service accepts the request
      Given Danny has 20 euro in his euro Revolut account
      And Danny selects 15 euro as the topUp amount
      And  Danny selects his BankAccount as his topUp method
    #And  Danny selects his BankAccount as his topUp method
      When Danny tops up
      Then The new balance of his euro account should now be 35



    Scenario Outline: Convert various amounts to in one currency to another currency within Revolut account
      Given Danny has a starting balance of <startBalance> in his <StartingCurrency>
      And Danny selects his <FinishCurrency> as the currency to convert to
      When Danny now converts his money
      Then The balance in his <FinishCurrency> account should be <finishBalance>
      Examples:
        | startBalance | StartingCurrency | FinishCurrency | finishBalance  |
        | 100          | "EUR"            | "GBP"          | 86.0          |
        | 100          | "GBP"            | "EUR"          | 117.0         |
        | 100          | "USD"            | "EUR"          | 82.0          |
        | 50           | "EUR"            | "USD"          | 60.5          |
        | 75           | "GBP"            | "USD"          | 106.5         |
        | 21           | "USD"            | "GBP"          | 14.91         |