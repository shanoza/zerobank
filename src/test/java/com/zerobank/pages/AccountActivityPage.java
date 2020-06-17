package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AccountActivityPage extends BasePage {
    private static Select select;

    @FindBy(id = "aa_accountId")
    private WebElement accountDropDwn;

    @FindBy(tagName = "th")
    private List<WebElement> transactionTableHeaders;

    @FindBy(id = "aa_fromDate")
    private WebElement fromDate;

    @FindBy(id = "aa_toDate")
    private WebElement toDate;

    @FindBy(id = "aa_type")
    private WebElement transDropDwn;

    @FindBy(tagName = "button")
    private WebElement findBtn;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[1]")
    private List<WebElement> allDates;

    @FindBy(id = "aa_description")
    private WebElement description;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[2]")
    private List<WebElement> allDescriptions;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[3]")
    private List<WebElement> allDeposits;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[4]")
    private List<WebElement> allWithdrawals;

    @FindBy(xpath = "//div[@class='well']")
    private WebElement noResultsMsg;


    public String getAccountDropDownDefault() {
        Select select = new Select(accountDropDwn);
        return select.getFirstSelectedOption().getText();
    }

    public List<String> getAllAccountDropDownOptions() {
        select = new Select(accountDropDwn);
        return BrowserUtils.getTextFromWebElements(select.getOptions());
    }

    public List<String> getTransactionTableHeaders() {
        wait.until(ExpectedConditions.visibilityOfAllElements(transactionTableHeaders));
        return BrowserUtils.getTextFromWebElements(transactionTableHeaders);
    }

    public void accountActivityNavigationTab(String tabName) {
        wait.until((ExpectedConditions.elementToBeClickable(By.linkText(tabName)))).click();
    }

    public void inputDateRange(String startDate, String endDate) {
        wait.until(ExpectedConditions.visibilityOf(fromDate)).sendKeys(startDate);
        wait.until(ExpectedConditions.visibilityOf(toDate)).sendKeys(endDate);
    }

    public List<String> getAllDates() {
        wait.until(ExpectedConditions.visibilityOfAllElements(allDates));
        return BrowserUtils.getTextFromWebElements(allDates);
    }

    public boolean validateDate(String startDate, String endDate, List<String> allDates) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        Date date1 = format.parse(startDate);
        Date date2 = format.parse(endDate);

        for (int i = 0; i < allDates.size(); i++) {
            Date validationDate = format.parse(allDates.get(i));
            if (date1.compareTo(validationDate) * date2.compareTo(validationDate) <= 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isContains(String searchingElement, List<String> range) {
        for (String each : range) {
            if (each.toUpperCase().contains(searchingElement)) return true;
        }
        return false;
    }

    public void clickFind() {
        wait.until(ExpectedConditions.visibilityOf(findBtn)).click();
    }

    public void addDescription(String descriptionText) {
        wait.until(ExpectedConditions.visibilityOf(description)).sendKeys(descriptionText);
    }

    public List<String> getAllDescriptions() {
        wait.until(ExpectedConditions.visibilityOfAllElements(allDescriptions));
        return BrowserUtils.getTextFromWebElements(allDescriptions);
    }

    public String noResultsMessage() {
        return wait.until(ExpectedConditions.visibilityOf(noResultsMsg)).getText().trim();
    }

    public void selectTypeDropDown(String type) {
        select = new Select(transDropDwn);
        select.selectByVisibleText(type);
    }

    public List<String> getAllWithdrawals(){
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(allWithdrawals));
        return BrowserUtils.getInnerHTMLFromWebElements(allWithdrawals);
    }

    public List<String> getAllDeposits(){
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(allDeposits));
        return BrowserUtils.getInnerHTMLFromWebElements(allDeposits);
    }


}