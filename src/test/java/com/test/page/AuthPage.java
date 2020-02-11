package com.test.page;

import com.test_demo.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePage {

    @FindBy(id = "email")
    WebElement loginField;

    @FindBy(id = "passwd")
    WebElement passField;

    @FindBy(id = "SubmitLogin")
    WebElement submitButton;

    @FindBy(xpath = "//div[@id='center_column']//ol/li")
    WebElement errorLabel;

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    // Enter login
    public AuthPage enterLogin(String login) {
        enterTextInTextField(loginField, login);
        return this;
    }

    // Enter password
    public AuthPage enterPassword(String password) {
        enterTextInTextField(passField, password);
        return this;
    }

    // Click Submit Button
    public BasePage submit() {
        clickWebElement(submitButton);
        return this;
    }

    // Get error message
    public String getErrorMessage() {
        return errorLabel.getText().trim();
    }
}