package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class OnlineStatementsPage extends BasePage {
    private String fileName;

    @FindBy(xpath = "(//h2)[1]")
    WebElement pageHeader;

    @FindBy(xpath = "//div[@class='tab-pane active']//tbody/tr")
    List<WebElement> statements;


    public String getPageHeader() {
        return wait.until(ExpectedConditions.visibilityOf(pageHeader)).getText().trim();
    }

    public OnlineStatementsPage navigateYears(String year) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(year))).click();
        return this;
    }

    public List<String> getStatements() {
        return BrowserUtils.getTextFromWebElements(statements);
    }

    public String getStatementsQty (){
        wait.until(ExpectedConditions.visibilityOfAllElements(statements));
        return String.valueOf(BrowserUtils.getTextFromWebElements(statements).size());
    }

    /**
     * @param statementName
     * @return OnlineStatementPage object
     */
    public void clickOnStatement(String statementName) {

        String xpath = "//a[text()='"+statementName+"']";
        WebElement link = driver.findElement(By.xpath(xpath));
        fileName = getDownloadedFileName(link);
        System.out.println("fileName = " + fileName);
        wait.until(ExpectedConditions.elementToBeClickable(link)).click();
        String filePath = "";
        if (System.getProperty("os.name").contains("mac")) {
            filePath = System.getProperty("user.home") + "/Downloads/" + fileName;
            System.out.println("filePath = " + filePath);
        } else {
            filePath = System.getProperty("user.home") + "\\Downloads\\" + fileName;
            System.out.println("filePath = " + filePath);
        }

        File file = new File(filePath);

        int timeout = 0;
        while (!file.exists() && timeout < 10) {
            BrowserUtils.wait(1);
            timeout++;
        }
        if (timeout == 10)
            throw new RuntimeException("Click Statement step: File is not downloaded");
    }


    public String getDownloadedFileName(WebElement link) {
        String linkAddress = link.getAttribute("href");
        return linkAddress.substring(linkAddress.indexOf("=") + 1).trim();
    }

    public boolean isFileNameContains(String name) {
        System.out.println("fileName = " + fileName);
        return fileName.contains(name);
    }
}