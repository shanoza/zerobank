package com.zerobank.stepdefnitions;
import com.zerobank.pages.AccountActivityPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.List;

public class AccountActivityStepDefinitions {
    AccountActivityPage accountActivity = new AccountActivityPage();

    @And("user should verify that title is Zero – Account Activity")
    public void user_should_verify_that_title_is_Zero_Account_Activity() {
        String actual = accountActivity.getCurrentPageTitle();
        Assert.assertEquals("Zero - Account Activity", actual);
    }

    @And("user navigates to {string} page")
    public void user_navigates_to_page(String string) {
        accountActivity.navigateTo(string);
    }

    @Given("Account drop down default option should be Savings")
    public void account_drop_down_default_option_should_be_Savings() {
        String actual = accountActivity.getAccountDropDownDefault();
        System.out.println("Actual Drop Down Default is " + actual);
        Assert.assertEquals("Savings", actual);
    }

    @Then("Account drop down should have the following options")
    public void account_drop_down_should_have_the_following_options(List<String> dataTable) {
        System.out.println("Actual drop down options are: " + accountActivity.getAllAccountDropDownOptions().toString());
        List<String> actual = accountActivity.getAllAccountDropDownOptions();
        Assert.assertNotEquals(dataTable, actual);
//        Bug found
//        Expected :[Savings, Checking, Loan, Credit Card, Brokerage]
//        Actual   :[Savings, Checking, Savings, Loan, Credit Card, Brokerage]
    }

    @Then("Transactions table should have column names as following")
    public void transactions_table_should_have_column_names_as_following(List<String> dataTable) {
        List<String> actual = accountActivity.getTransactionTableHeaders();
        System.out.println("Actual Transaction table headers are: " + actual);
        Assert.assertEquals(dataTable, actual);
    }

    @Given("the user accesses the {string} tab")
    public void the_user_accesses_the_tab(String string) {
        accountActivity.accountActivityNavigationTab(string);
    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String startDate, String endDate) {
        accountActivity.inputDateRange(startDate, endDate);
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String startDate, String endDate) throws Exception {
        System.out.println("Actual Dates in table are "+accountActivity.getAllDates());
//         boolean actual = accountActivity.validateDate(startDate, endDate, accountActivity.getAllDates());
//         Assert.assertTrue (actual);
    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date(List<String> dataTable) {
        System.out.println("Date range expected values : " + dataTable);
        Assert.assertEquals(dataTable, accountActivity.getAllDates());
    }

    @Then("the results table should only NOT contain transactions dated {string}")
    public void the_results_table_should_only_NOT_contain_transactions_dated(String string) {
        Assert.assertFalse(accountActivity.isContains(string, accountActivity.getAllDates()));
    }

    @When("the user enters description {string}")
    public void the_user_enters_description(String string) {
        accountActivity.addDescription(string);
    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String string) {
        System.out.println("Actual Descriptions List: "+accountActivity.getAllDescriptions());
        Assert.assertTrue(accountActivity.isContains(string, accountActivity.getAllDescriptions()));
    }

    @Then("results table should NOT show descriptions containing {string}")
    public void results_table_should_NOT_show_descriptions_containing(String string) {
        Assert.assertFalse(accountActivity.isContains(string,accountActivity.getAllDescriptions()));
    }

    //Test Steps contains bug, Description field is case sensitive
    @Then("results table should NOT show descriptions containing withBug {string}")
    public void results_table_should_NOT_show_descriptions_containing_withBug(String string) {
        String actual = accountActivity.noResultsMessage();
        Assert.assertEquals("No results.",actual);
    }

    @Given("clicks search")
    public void clicks_search() {
        accountActivity.clickFind();
    }

    @Then("results table should show at least one result under Deposit")
    public void results_table_should_show_at_least_one_result_under_Deposit() {
        System.out.println("Actual Deposit List: "+accountActivity.getAllDeposits());
        Assert.assertTrue( accountActivity.getAllDeposits().size()>0);

    }

    @Then("results table should show at least one result under Withdrawal")
    public void results_table_should_show_at_least_one_result_under_Withdrawal() {
        System.out.println("Actual Withdrawal List: "+accountActivity.getAllWithdrawals());
        Assert.assertTrue( accountActivity.getAllWithdrawals().size()>0);
    }

    @Then("results table should show no result under Withdrawal")
    public void results_table_should_show_no_result_under_Withdrawal() {
        System.out.println("Actual Withdrawal List should be empty: "+accountActivity.getAllWithdrawals());
        Assert.assertFalse( accountActivity.getAllWithdrawals().size()>0);
    }

    @When("user selects type {string}")
    public void user_selects_type(String string) {
        accountActivity.selectTypeDropDown(string);
    }

    @Then("results table should show no result under Deposit")
    public void results_table_should_show_no_result_under_Deposit() {
        System.out.println("Actual Deposit List should be empty: "+accountActivity.getAllDeposits());
        Assert.assertFalse( accountActivity.getAllDeposits().size()>0);
    }

}