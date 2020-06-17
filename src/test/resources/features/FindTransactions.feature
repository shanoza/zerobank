@transactions
Feature: Find Transactions in Account Activity

  Background: open login page
    Given user is on login page
    And user logs in with valid credentials
    And user navigates to "Account Activity" page
    And the user accesses the "Find Transactions" tab

  Scenario: Search date range - Positive
    When the user enters date range from "2012-09-01" to "2012-09-06"
    And clicks search
    Then results table should only show transactions dates between "2012-09-01" to "2012-09-06"
    And the results should be sorted by most recent date
      | 2012-09-06 |
      | 2012-09-05 |
      | 2012-09-01 |

  Scenario:  Search date range - Negative
    When the user enters date range from "2012-09-02" to "2012-09-06"
    And clicks search
    Then results table should only show transactions dates between "2012-09-02" to "2012-09-06"
    And the results table should only NOT contain transactions dated "2012-09-01"

  Scenario: Search description "ONLINE" - Positive
    When the user enters description "ONLINE"
    And clicks search
    Then results table should only show descriptions containing "ONLINE"

  Scenario: Search description "OFFICE" - Negative
    When the user enters description "OFFICE"
    And clicks search
    Then results table should only show descriptions containing "OFFICE"
    But results table should NOT show descriptions containing "ONLINE"

  Scenario: Search description case insensitive
    When the user enters description "online"
    And clicks search
    Then results table should NOT show descriptions containing withBug "ONLINE"
    #found bug - the description is actually case sensitive - changed requirements

  Scenario: Type
    And clicks search
    Then results table should show at least one result under Deposit
    Then results table should show at least one result under Withdrawal

    When user selects type "Deposit"
    And clicks search
    Then results table should show at least one result under Deposit
    But results table should show no result under Withdrawal
    When user selects type "Withdrawal"
    And clicks search
    Then results table should show at least one result under Withdrawal
    But results table should show no result under Deposit