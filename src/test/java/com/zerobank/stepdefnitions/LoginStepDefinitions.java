package com.zerobank.stepdefnitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;


public class LoginStepDefinitions {
    LoginPage loginPage = new LoginPage();

    @Given("user is on login page")
    public void user_on_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("URL"));
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        loginPage.login();
    }

    @Then("account summary page should be displayed.")
    public void account_summary_page_should_be_displayed() {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        boolean actual = accountSummaryPage.getCurrentPageTitle().contains("Account Summary");
        Assert.assertTrue(actual);
    }

    @When("user logs in wrong username or wrong password")
    public void user_logs_in_wrong_username_or_wrong_password() {
        loginPage.login("wrongUserName", "WrongPassword");
    }

    @Then("error message Login and password are wrong should be displayed.")
    public void error_message_Login_and_or_password_are_wrong_should_be_displayed() {
        String actual = loginPage.getAlertMsg();
        Assert.assertEquals("Login and/or password are wrong.", actual);
    }

    @When("user logs in with blank username or wrong password")
    public void user_logs_in_with_blank_username_or_wrong_password() {
        loginPage.login(" ", " ");
    }
}