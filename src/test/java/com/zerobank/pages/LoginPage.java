package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy (id="user_login")
    WebElement username;

    @FindBy (id = "user_password")
    WebElement password;

    @FindBy (xpath = "//form/div[@class='alert alert-error']")
    WebElement alertMsg;

    @FindBy (name = "submit")
    WebElement submitBtn;

    public void login (){
        clickSignInBtn();
        BrowserUtils.waitForPageToLoad(25);
        username.sendKeys(ConfigurationReader.getProperty("username"));
        password.sendKeys(ConfigurationReader.getProperty("password"));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        BrowserUtils.waitForPageToLoad(10);
    }

    public void login (String usernameValue, String passwordValue){
        clickSignInBtn();
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue);
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        BrowserUtils.waitForPageToLoad(10);
    }

    public String getAlertMsg() {
        return alertMsg.getText();
    }
}