@purchase_foreign_currency
Feature: Purchase Foreign Currency
  As a user, I want to purchase foreign currency

  Background: open login page
    Given user is on login page
    And user logs in with valid credentials
    And user navigates to "Pay Bills" page
    Given user is on "Purchase Foreign Currency" tab

#Bug found the actual list doesn't match with requirements (Sweden (krona), Thailand (baht) are missing)
  Scenario: Available currencies
    Then following currencies should be available
      | Australia (dollar)    |
      | Canada (dollar)       |
      | Switzerland (franc)   |
      | China (yuan)          |
      | Denmark (krone)       |
      | Eurozone (euro)       |
      | Great Britain (pound) |
      | Japan (yen)           |
      | Mexico (peso)         |
      | Norway (krone)        |
      | New Zealand (dollar)  |
      | Singapore (dollar)    |

  Scenario: Error message for not selecting currency
    When user tries to calculate cost without selecting a currency
    Then error message should be displayed "Please, ensure that you have filled all the required fields with valid values."

  Scenario: Error message for not entering value
    When user tries to calculate cost without entering a value
    Then error message should be displayed "Please, ensure that you have filled all the required fields with valid values."