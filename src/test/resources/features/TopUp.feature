Feature: TopUp Account
  This feature describes various scenarios for users adding funds to their revolut account(s)

  #As a user, I can topup my Revolut account using my debit card

  Scenario: Add money to Revolut account using debit card
    Given Danny has 10 EUR in his Revolut account
    And Danny selects 100 EUR as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his EUR account should now be 110


  Scenario: Add money to Revolut account using bank account
    Given Danny has 20 EUR in his Revolut account
    And Danny selects 230 EUR as the topUp amount
    And  Danny selects his BankAccount as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his EUR account should now be 250



  #ToDo implement the remaining scenarios listed below

  #To implement this scenario you will need to use data tables
    # https://cucumber.io/docs/cucumber/api/
  Scenario Outline: Add various amounts to Revolut account
    Given Danny has a starting balance of <startBalance>
    And Danny selects his DebitCard as his topUp method
    When Danny now tops up by <topUpAmount>
    Then The new balance of his EUR account should now be <newBalance>
    Examples:
      | startBalance| topUpAmount | newBalance  |
      | 0           | 100         | 100         |
      | 14          | 20          | 34          |
      | 23          | 30          | 53          |

  Rule: The account balance shouldn't change if the topup payment request is rejected by the payment service

    #The scenarios below will need a payment service that accepts or rejects a request to add funds
    Scenario: Payment service rejects the request
      Given Danny has 30 EUR in his Revolut account
      And Danny selects 100 EUR as the topUp amount
      And  Danny selects his DebitCard as his topUp method
      And The DebitCard Rejects the request
      When Danny tops up
      Then The new balance of his EUR account should now be 30

    Scenario: Payment service accepts the request
      Given Danny has 30 EUR in his Revolut account
      And Danny selects 100 EUR as the topUp amount
      And  Danny selects his DebitCard as his topUp method
      And The DebitCard Accepts the request
      When Danny tops up
      Then The new balance of his EUR account should now be 130