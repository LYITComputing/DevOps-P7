Feature: Split Bill
  This feature describes scenarios for friends splitting a bill

  #As a user, I can split a bill with my friends so we all pay the same amount

  Scenario Outline: Split a bill between friends
    Given The total bill is <billAmount> euro
    And Danny has a start balance of <dannyStartBalance>
    And Mark has a start balance of <markStartBalance>
    When Danny and Mark split the bill
    Then Danny's new balance should be <dannyNewBalance>
    And Mark's new balance should be <markNewBalance>
    Examples:
      | dannyStartBalance| markStartBalance | billAmount       | dannyNewBalance  | markNewBalance   |
      | 100              | 200              | 100              | 50               | 150              |
      | 140              | 75               | 150              | 65               | 0                |
      | 230              | 300              | 200              | 130              | 200              |

    Rule: The transaction is cancelled if one of the friends has insufficient funds

        Scenario: One of the friends has insufficient funds
          Given The total bill is 100 euro
          And Danny has a start balance of 25 euro
          And Mark has a start balance of 75 euro
          When Danny and Mark split the bill
          Then The transaction should be cancelled
          And Danny's balance should be 25 euro
          And Mark's balance should be 75 euro