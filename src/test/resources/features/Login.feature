@login
Feature: Login functionality


  @authorized_user
  Scenario: Only authorized users should be able to login to the application.
    Given user is on login page
    When user logs in with valid credentials
    Then account summary page should be displayed.

  @unauthorized_user_wrong
  Scenario: Users with wrong username or wrong password should not be able to login.
    Given user is on login page
    When user logs in wrong username or wrong password
    Then error message Login and password are wrong should be displayed.

  @unauthorized_user-blank
  Scenario: Users with blank username or password should not be able to login.
    Given user is on login page
    When user logs in with blank username or wrong password
    Then error message Login and password are wrong should be displayed.