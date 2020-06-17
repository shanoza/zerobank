package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractBasePage {
    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver,15);

    public AbstractBasePage(){
        PageFactory.initElements(driver,this);
    }
    public void navigateTo(String tabName) {
        String tabNameXpath= "//a[text()='" + tabName + "')]";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(tabNameXpath))).click();
    }
}
