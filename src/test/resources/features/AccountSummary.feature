@account
Feature: Navigating to specific accounts in Accounts Activity
  Background: open login page
    Given user is on login page
    And user logs in with valid credentials
    And user navigates to "Account Summary" page

  Scenario Outline: <Account_Type> redirect
    When the user clicks on "<Account_Type>" link on the Account Summary page
    Then the user verifies "Account Activity" page is displayed
    And Account drop down default option should be "<Account_Type>"
    Examples:
      | Account_Type |
      | Savings      |
      | Brokerage    |
      | Checking     |
      | Credit Card  |
      | Loan         |