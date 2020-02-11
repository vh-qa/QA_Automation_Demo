package com.test_demo.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private final int EXPLICITLY_WAIT_TIMEOUT = 10;
    private final static Logger LOG = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Get text from web element
    protected String getWebElementText(WebElement webElement){
        return webElement.getText().trim();
    }

    // Enter text to text field
    public static void enterTextInTextField(WebElement textField, String inputText) {
        textField.click();
        textField.clear();
        textField.sendKeys(inputText);
    }

    // Click on web element
    public static void clickWebElement(WebElement webElement){
        webElement.click();
    }

    // Waiting for element appearance during waitTime
    protected void waitUntilElementIsVisible(Integer waitTime, WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
