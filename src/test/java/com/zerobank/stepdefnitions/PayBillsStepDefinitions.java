package com.zerobank.stepdefnitions;

import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class PayBillsStepDefinitions {
    PayBillsPage payBillsPage = new PayBillsPage();

    @Given("user should verify that title is Zero – Pay Bills")
    public void user_should_verify_that_title_is_Zero_Pay_Bills() {
        Assert.assertEquals("Zero - Pay Bills", payBillsPage.getCurrentPageTitle());
    }

    @Given("user is on {string} tab")
    public void user_is_on_tab(String string) {
        payBillsPage.navigateToPayBillsTabs(string);
        BrowserUtils.waitForPageToLoad(25);
    }


    @When("user chooses payee {string}")
    public void user_chooses_payee(String string) {
        payBillsPage.selectPayeeDropDown(string);
    }

    @When("user chooses {string} account")
    public void user_chooses_account(String string) {
        payBillsPage.selectAccountDropDown(string);
    }

    @Then("user enters payment information")
    public void user_enters_payment_information(List<Map<String, String>> dataTable) {
        for (Map<String, String> row : dataTable) {
            payBillsPage.enterPaymentInformation(row.get("amount"), row.get("date"), row.get("description"));
        }
    }

    @Then("user clicks on Pay button")
    public void user_clicks_on_Pay_button() {
        payBillsPage.clickOnSaveBtn();
    }

    @Then("user verifies that The payment was successfully submitted. message should be displayed")
    public void user_verifies_that_The_payment_was_successfully_submitted_message_should_be_displayed() {
        String actualMsg = payBillsPage.getSubmissionMsg();
        Assert.assertEquals("The payment was successfully submitted.", actualMsg);
    }

    @When("user enters date {string}")
    public void user_enters_date(String string) {
        payBillsPage.enterDate(string);
    }

    @When("user enters amount {string}")
    public void user_enters_amount(String string) {
        payBillsPage.enterAmount(string);
    }


    @Then("user verifies that Please fill out this field message. should be displayed under amount field")
    public void user_verifies_that_Please_fill_out_this_field_message_should_be_displayed_under_amount_field() {
        String actualAlertText = payBillsPage.getAmountNotFilledOutAlert();
        Assert.assertEquals("Please fill out this field.", actualAlertText);
    }

    @Then("user verifies that Please fill out this field message. should be displayed under date field")
    public void user_verifies_that_Please_fill_out_this_field_message_should_be_displayed_under_date_field() {
        String actualAlertText = payBillsPage.getDateNotFilledOutAlert();
        Assert.assertEquals("Please fill out this field.", actualAlertText);
    }


    @Given("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String, String> dataTable) {
        payBillsPage.addPayeeName(dataTable.get("Payee Name"))
                .addPayeeAddress("Payee Address")
                .addPayeeAccount("Account")
                .addPayeeDetails("Payee Details");

        payBillsPage.clickOnAddButton();
    }

    @Then("message The new payee The Law Offices of Hyde, Price & Scharks was successfully created. should be displayed")
    public void message_The_new_payee_The_Law_Offices_of_Hyde_Price_Scharks_was_successfully_created_should_be_displayed() {
        Assert.assertTrue(payBillsPage.getPayeeAddedMsg().contains("The new payee The Law Offices of Hyde, Price & Scharks was successfully created."));
    }
    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> dataTable) {
        Assert.assertNotEquals(dataTable, payBillsPage.getAllCurrencyOptions());
    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {
        payBillsPage.clickOnCalculateCostsBtn();
    }

    @Then("error message should be displayed {string}")
    public void error_message_should_be_displayed(String string) {
        Assert.assertEquals(string,payBillsPage.purchaseForeignCurrencyAlert());
    }

    @When("user tries to calculate cost without entering a value")
    public void user_tries_to_calculate_cost_without_entering_a_value() {
        payBillsPage.clickOnCalculateCostsBtn();
    }

}