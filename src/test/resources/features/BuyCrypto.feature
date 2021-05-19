Feature: Buy Crypto
  This feature describes various scenarios for Buying Crypto Currency

  #As a user, I can buy Crypto Currency with my Balance

  Scenario: Buy Bitcoin using Bank Account
    Given Danny has 0 BTC in his Revolut account
    And Danny has 100 EUR in his Revolut account
    And  Danny selects his EUR Account as his Buy method
    #And  Danny selects his BankAccount as his topUp method
    When Danny purchases BTC with 100 EUR
    Then The new balance of his BTC account should now be 0.0034
# Current Exchange Rate as of Today (19-05-21) from google

  Scenario: Sell Bitcoin using Bank Account
    Given Danny has 0.0034 BTC in his Revolut account
    And Danny selects 0.0034 BTC to Sell back to EUR
    #And  Danny selects his BankAccount as his topUp method
    When Danny Sells Bitcoin
    Then The new balance of his BTC account should now be 0
    Then The new balance of his EUR account should now be 108.46180199999999
# Current Exchange Rate as of Today (19-05-21) from google



  #ToDo implement the remaining scenarios listed below

  #To implement this scenario you will need to use data tables
    # https://cucumber.io/docs/cucumber/api/
  Scenario Outline: Transfer Between various Revolut Currencies
    Given Danny has <startFirstCurrencyAmount> <toCurrencyCode> in his Revolut account
    And Danny has <startSecondCurrencyAmount> <fromCurrencyCode> in his Revolut account
    And  Danny selects his <fromCurrencyCode> Account as his Buy method
    #And  Danny selects his BankAccount as his topUp method
    When Danny purchases <toCurrencyCode> with <startSecondCurrencyAmount> <fromCurrencyCode>
    Then The new balance of his <toCurrencyCode> account should now be <finalBalanceNewCurrency>
    And The new balance of his <fromCurrencyCode> account should now be <finalBalancefromCurrency>
    Examples:
      | startFirstCurrencyAmount| toCurrencyCode | startSecondCurrencyAmount  | fromCurrencyCode | finalBalanceNewCurrency | finalBalancefromCurrency |
      |0                        |BTC             |100                         |EUR               |0.0034                   |0                         |
      |0                        |BTC             |100                         |EUR               |0.0034                   |0                         |
      |0                        |BTC             |100                         |EUR               |0.0034                   |0                         |
      |0                        |BTC             |100                         |EUR               |0.0034                   |0                         |


