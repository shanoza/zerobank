package com.zerobank.stepdefnitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import io.cucumber.java.en.*;
import org.junit.Assert;


import java.util.List;

public class AccountSummaryStepDefinitions {
    AccountSummaryPage accountSummary = new AccountSummaryPage();
    AccountActivityPage accountActivity = new AccountActivityPage();

    @Then("user should verify that title is Zero – Account Summary")
    public void user_should_verify_that_title_is_Zero_Account_Summary() {
        String actual = accountSummary.getCurrentPageTitle();
        Assert.assertEquals("Zero - Account Summary",actual);
    }

    @Then("user should verify that page should have following types")
    public void user_should_verify_that_page_should_have_following_types(List<String> dataTable) {
        System.out.println("Account Summary Page expected values : " + dataTable);
        Assert.assertEquals(dataTable, accountSummary.getAccountTypeOptions());
    }

    @Then("user should verify that Credit Accounts have following options")
    public void user_should_verify_that_Credit_Accounts_have_following_options(List<String> dataTable) {
        System.out.println("Account Summary Page expected values : " + dataTable);
        Assert.assertEquals(dataTable, accountSummary.getCreditAccountsColumns());
    }

    @When("the user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_Account_Summary_page(String string) {
        accountSummary.clickOnAccountLink(string);
    }

    @Then("the user verifies {string} page is displayed")
    public void the_user_verifies_page_is_displayed(String string) {
        Assert.assertEquals("Zero - Account Activity", accountActivity.getCurrentPageTitle() );
    }

    @Then("Account drop down default option should be {string}")
    public void account_drop_down_default_option_should_be(String string) {
        Assert.assertEquals(string, accountActivity.getAccountDropDownDefault());
    }
}
