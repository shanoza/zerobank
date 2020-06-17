package com.zerobank.pages;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
/*
Account summaryAccount summary page should have the title Zero –Account summary. Account summary page should have to
following account types: Cash Accounts, Investment Accounts, Credit Accounts, Loan Accounts.
Credit Accounts table must have columnsAccount, Credit Cardand Balance.
 */

public class AccountSummaryPage extends BasePage {

    @FindBy (className = "board-header")
    private List<WebElement> accountTypes;

    @FindBy (xpath = "(//h2[text()='Credit Accounts']/following-sibling::div//table)[1]//th")
    private List<WebElement> creditAccountColumns;

    public List<String> getAccountTypeOptions(){
        wait.until(ExpectedConditions.visibilityOfAllElements(accountTypes));
        return BrowserUtils.getTextFromWebElements(accountTypes);
    }

    public List<String> getCreditAccountsColumns(){
        wait.until(ExpectedConditions.visibilityOfAllElements(creditAccountColumns));
        return BrowserUtils.getTextFromWebElements(creditAccountColumns);
    }

    public void clickOnAccountLink(String accountType){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(accountType)))).click();
        BrowserUtils.waitForPageToLoad(25);
    }

}