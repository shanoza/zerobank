@account_activity
Feature: Account activity events
  As a user, I want to see activity events
  #Account ActivityAccount Activity page should have the title Zero –Account activity.
  #In the Account drop down default option should be Savings.
  #Account drop down should have the following options:
  #Savings, Checking, Loan, Credit Card, Brokerage.
  #Transactions table should have column names Date, Description, Deposit, Withdrawal.

  Background: open login page
    Given user is on login page
    And user logs in with valid credentials
    And user navigates to "Account Activity" page
    And user should verify that title is Zero – Account Activity


  Scenario: Account drop down default option should be Savings
    Given Account drop down default option should be Savings

  Scenario: Account Drop Down options using dataTable
    Then Account drop down should have the following options
      | Savings     |
      | Checking    |
      | Loan        |
      | Credit Card |
      | Brokerage   |

  Scenario: Transaction Table column names using dataTable
    Then Transactions table should have column names as following
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |