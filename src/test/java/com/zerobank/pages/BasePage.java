package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait (driver, 20);

    public BasePage (){
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "signin_button")
    protected WebElement signInBtn;

    @FindBy (xpath = "//a[contains(@href,'/bank/redirect.html')")
    protected WebElement tab;

    public void clickSignInBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();
    }

    public void navigateTo(String tabName){
        String xpath = "//a[contains(@href,'/bank/redirect.html') and text()='"+tabName+"']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        WebElement tabElement = driver.findElement(By.xpath(xpath));
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(tabElement));
        actions.moveToElement(tabElement).pause(2000).click(tabElement).build().perform();
        BrowserUtils.waitForPageToLoad(20);
    }

    public String getCurrentPageTitle(){
        return driver.getTitle();
    }

    public String getSelectedTabName(){
        return tab.getText();
    }

}