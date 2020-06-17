package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PayBillsPage extends BasePage {
    private Select select;
    private Alert alert;

    @FindBy(id = "sp_payee")
    private WebElement payeeDropDown;

    @FindBy (id = "sp_account")
    private WebElement accountDropDown;

    @FindBy (id="sp_amount")
    private WebElement amount;

    @FindBy (id = "sp_date")
    private WebElement date;

    @FindBy (id = "sp_description")
    private WebElement description;

    @FindBy (id = "pay_saved_payees")
    private WebElement payBtn;

    @FindBy (xpath = "//span[contains(@title,'payed to payee')]")
    private WebElement paymentSubmissionMsg;

    @FindBy (id = "np_new_payee_name")
    private WebElement newPayeeName;

    @FindBy (id = "np_new_payee_address")
    private WebElement newPayeeAddress;

    @FindBy (id="np_new_payee_account")
    private WebElement newPayeeAccount;

    @FindBy (id ="np_new_payee_details")
    private WebElement newPayeeDetails;

    @FindBy (id ="add_new_payee" )
    private WebElement addButton;

    @FindBy (id ="alert_content")
    private WebElement payeeAddedMsg;

    @FindBy (id="pc_currency")
    private WebElement currencyOptions;

    @FindBy (id = "pc_amount")
    private WebElement currencyAmount;

    @FindBy (id = "purchase_cash")
    private WebElement purchaseBtn;

    @FindBy (id = "pc_calculate_costs")
    private WebElement calculateCostsBtn;

    public PayBillsPage navigateToPayBillsTabs(String tabName){
        BrowserUtils.waitForPageToLoad(25);
        String xpath = "//div[@id='tabs']//a[text()='"+tabName+"']";
        WebElement tabElement = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.visibilityOf(tabElement));
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(tabElement));
        actions.moveToElement(tabElement).pause(2000).click(tabElement).build().perform();
        return this;
    }

    public PayBillsPage selectPayeeDropDown (String payee) {
        Select select = new Select(payeeDropDown);
        select.selectByVisibleText(payee);
        wait.until(ExpectedConditions.textToBePresentInElement(payeeDropDown,payee));
        return this;
    }

    public PayBillsPage selectAccountDropDown (String account){
        Select select = new Select(accountDropDown);
        select.selectByVisibleText(account);
        wait.until(ExpectedConditions.textToBePresentInElement(accountDropDown, account));
        return this;
    }

    public PayBillsPage enterPaymentInformation (String addAmount, String addDate, String addDescription){
        wait.until(ExpectedConditions.visibilityOf(amount)).sendKeys(addAmount);
        wait.until(ExpectedConditions.visibilityOf(date)).sendKeys(addDate);
        wait.until(ExpectedConditions.visibilityOf(description)).sendKeys(addDescription);
        return this;
    }

    public PayBillsPage enterAmount(String addAmount){
        wait.until(ExpectedConditions.visibilityOf(amount)).sendKeys(addAmount);
        return this;
    }

    public PayBillsPage enterDate(String addDate){
        wait.until(ExpectedConditions.visibilityOf(date)).sendKeys(addDate);
        return this;
    }

    public String getAmountNotFilledOutAlert(){
        return wait.until(ExpectedConditions.visibilityOf(amount)).getAttribute("validationMessage");
    }

    public String getDateNotFilledOutAlert(){
        return wait.until(ExpectedConditions.visibilityOf(date)).getAttribute("validationMessage");
    }

    public PayBillsPage clickOnSaveBtn (){
        payBtn.click();
        return this;
    }

    public String getSubmissionMsg (){
        return wait.until(ExpectedConditions.visibilityOf(paymentSubmissionMsg)).getText();
    }

    public void clickOnAddButton(){
        wait.until(ExpectedConditions.visibilityOf(addButton)).click();
    }

    public PayBillsPage addPayeeName(String name){
        wait.until(ExpectedConditions.visibilityOf(newPayeeName)).sendKeys(name);
        return this;
    }
    public PayBillsPage addPayeeAddress(String address){
        wait.until(ExpectedConditions.visibilityOf(newPayeeAddress)).sendKeys(address);
        return this;
    }
    public PayBillsPage addPayeeAccount (String account){
        wait.until(ExpectedConditions.visibilityOf(newPayeeAccount)).sendKeys(account);
        return this;
    }

    public PayBillsPage addPayeeDetails (String details){
        wait.until(ExpectedConditions.visibilityOf(newPayeeDetails)).sendKeys(details);
        return this;
    }

    public String getPayeeAddedMsg(){
        return wait.until(ExpectedConditions.visibilityOf(payeeAddedMsg)).getText();
    }

    public List<String> getAllCurrencyOptions(){
        wait.until(ExpectedConditions.visibilityOf(currencyOptions));
        select = new Select(currencyOptions);
        return BrowserUtils.getTextFromWebElements(select.getOptions());
    }

    public PayBillsPage addCurrencyAmount(String amount){
        wait.until(ExpectedConditions.visibilityOf(currencyAmount)).sendKeys(amount);
        return this;
    }

    public void clickOnPurchaseBtn (){
        wait.until(ExpectedConditions.visibilityOf(purchaseBtn)).click();
    }

    public String purchaseForeignCurrencyAlert(){
        alert = wait.until(ExpectedConditions.alertIsPresent());
        if (alert!=null){
            System.out.println("Alert is present");
            return driver.switchTo().alert().getText();
        } else {
            return "Alert is not present";
        }
    }

    public void clickOnCalculateCostsBtn(){
        wait.until(ExpectedConditions.visibilityOf(calculateCostsBtn)).click();
    }

}