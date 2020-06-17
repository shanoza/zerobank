package com.zerobank.stepdefnitions;

import com.zerobank.pages.OnlineStatementsPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.io.File;
import java.nio.file.Path;

public class OnlineStatementsStepDefinitions {
    private OnlineStatementsPage onlineStatements = new OnlineStatementsPage();

    @Given("the user accesses the Statements & Documents tab")
    public void the_user_accesses_the_Statements_Documents_tab() {
        Assert.assertEquals("Statements & Documents",onlineStatements.getPageHeader());
    }

    @Then("{string} statements should be displayed for that year")
    public void statements_should_be_displayed_for_that_year(String string) {
        System.out.println("Statements present are: "+onlineStatements.getStatements());
        Assert.assertEquals(string, onlineStatements.getStatementsQty());
    }

    @Given("the user selects the Recent Statements Year {string}")
    public void the_user_selects_the_Recent_Statements_Year(String string) {
        System.out.println("Selected year is: "+string);
        onlineStatements.navigateYears(string);
    }

    @When("the user clicks on statement {string}")
    public void the_user_clicks_on_statement(String string) {
        System.out.println("Selected Statement name is: "+string);
        onlineStatements.clickOnStatement(string);
    }

    @Then("the downloaded file name should contain {string}")
    public void the_downloaded_file_name_should_contain(String string) {
        Assert.assertTrue(onlineStatements.isFileNameContains(string));
    }

    @Then("the file type should be pdf")
    public void the_file_type_should_be_pdf() {
        Assert.assertTrue(onlineStatements.isFileNameContains("pdf"));
    }
}