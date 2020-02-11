package com.test.page;

import com.test_demo.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@class='login' and contains(text(), 'Sign in')]")
    private WebElement loginLink;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public AuthPage clickEnterLink() {
        loginLink.click();
        return new AuthPage(driver);
    }
}