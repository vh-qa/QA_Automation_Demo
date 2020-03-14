package com.test_demo.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.FileAssert.fail;

public class BaseTest {
    protected WebDriver driver;
    protected StringBuffer verificationErrors = new StringBuffer();
    private final static Logger LOG = LogManager.getLogger(BasePage.class);

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws Exception {
        initDrivers(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private void initDrivers(String browser) {

//      String commonProperties = System.getProperty("automation.practice.cfg");

        String commonProperties = "src/main/resources/common.properties";
        Properties properties = new Properties();

        if(commonProperties != null) {
            try {
                properties.load(new FileReader(commonProperties));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("gecko.driver"));
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unknown browser " + browser);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public void getProperties(String propertyFilePath, String propertyName){

    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] params) {
        LOG.info("Start test {} with parameters {}",
                method.getName(), Arrays.toString(params));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        LOG.info("Stop test {}", method.getName());
    }
}


