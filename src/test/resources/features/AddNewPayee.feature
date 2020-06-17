@add_payee
Feature: Add new payee under pay bills
  As a user, I want to add payees

  Background: open login page
    Given user is on login page
    And user logs in with valid credentials
    And user navigates to "Pay Bills" page

  Scenario: Add a new payee

    Given user is on "Add New Payee" tab
    And creates new payee using following information
      | Payee Name    | The Law Offices of Hyde, Price & Scharks |
      | Payee Address | 100 Same st, Anytown, USA, 10001         |
      | Account       | Checking                                 |
      | Payee details | XYZ account                              |
    Then message The new payee The Law Offices of Hyde, Price & Scharks was successfully created. should be displayed