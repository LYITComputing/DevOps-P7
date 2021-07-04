@CurrEx
Feature: Currency Exchange
  This feature describes various scenarios for users converting to different currencies

  #As a user, I can exchange money from one currency to another

  Scenario: Convert money from Euro to US Dollars
    Given Lorraine has 250 euro in her euro Revolut account
    And Lorraine selects 100 euro to convert
    And  Lorraine selects USD as her exchange currency
    When Lorraine exchanges at a rate of 1.19
    Then The new balance of her euro account should now be 150
    And a new USD account should have balance of 119

  Scenario: Convert money from Euro to US Dollars where US account already exists
    Given Lorraine has 900 euro in her euro Revolut account
    And Lorraine has 400 USD in her USD Revolut account
    And Lorraine selects 200 euro to convert
    And  Lorraine selects USD as her exchange currency
    When Lorraine exchanges at a rate of 1.19
    Then The new balance of her euro account should now be 700
    And a new USD account should have balance of 638