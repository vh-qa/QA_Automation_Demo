package com.test;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridTest {

    private WebDriver driver;
    private String baseUrl;
    private String nodeUrl;

    @Test
    public void seleniumGridSimpleTest() {
        driver.get(baseUrl);
    }

    @BeforeTest
    public void beforeTest() throws MalformedURLException {
        baseUrl = "https://ek.ua/";
        nodeUrl = "http://192.168.1.3:5555/wd/hub";

//        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
//        desiredCapabilities.setBrowserName("chrome");
//        desiredCapabilities.setPlatform(Platform.WIN10);

        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.addArguments("headless"); //Running tests without opening a browser window

        driver = new RemoteWebDriver(new URL(nodeUrl), options);
        driver.manage().window().maximize();

//        ChromeOptions cap = new ChromeOptions();
//        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
//                UnexpectedAlertBehaviour.IGNORE);

//        driver = new RemoteWebDriver(
//                new URL(nodeUrl), desiredCapabilities);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}