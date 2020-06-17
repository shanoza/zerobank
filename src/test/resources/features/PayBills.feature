@pay_bills
Feature: Pay Bills events
  As a user, I want to be able to make a payment
#Pay BillsAccount Activity page should have the title Zero –Pay Bills.
#When user completes a successful Pay operation,
#The payment was successfully submitted. should be displayed.
#When user tries to make a payment without entering the amount or date,
#Please fill out this field message!should be displayed.
#Amount field should not accept alphabetical or special characters.
#Date field should not accept alphabetical characters.
#NOTE: For the date input field,you can just use sendKeys.
#No need to click on the date navigator.# new feature
  Background: open login page
    Given user is on login page
    And user logs in with valid credentials
    And user navigates to "Pay Bills" page
    And user should verify that title is Zero – Pay Bills

  Scenario: Making payment - Positive
    Given user is on "Pay Saved Payee" tab
    When user chooses payee "Bank of America"
    And user chooses "Savings" account
    Then user enters payment information
      | amount | date       | description          |
      | 100    | 2020-05-06 | some deposit payment |
    And user clicks on Pay button
    Then user verifies that The payment was successfully submitted. message should be displayed

  Scenario: Making payment without entering amount - Negative
    Given user is on "Pay Saved Payee" tab
    When user chooses payee "Bank of America"
    And user chooses "Savings" account
    And user enters date "2020-05-06"
    And user clicks on Pay button
    Then user verifies that Please fill out this field message. should be displayed under amount field

  Scenario: Making payment without entering date - Negative
    Given user is on "Pay Saved Payee" tab
    When user chooses payee "Bank of America"
    And user chooses "Savings" account
    And user enters amount "100"
    And user clicks on Pay button
    Then user verifies that Please fill out this field message. should be displayed under date field

    #must fail due to a bug on webPage -> the form accepts alphabetical characters
  Scenario: Entering alphabetical characters into amount field - Positive
    Given user is on "Pay Saved Payee" tab
    When user chooses payee "Bank of America"
    And user chooses "Savings" account
    And user enters amount "ABC"
    And user enters date "2020-05-06"
    And user clicks on Pay button
    Then user verifies that Please fill out this field message. should be displayed under amount field

  Scenario: Entering alphabetical characters into date field - Positive
    Given user is on "Pay Saved Payee" tab
    When user chooses payee "Bank of America"
    And user chooses "Savings" account
    And user enters amount "100"
    And user enters date "ABC"
    And user clicks on Pay button
    Then user verifies that Please fill out this field message. should be displayed under date field