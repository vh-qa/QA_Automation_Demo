package com.test;

import com.test.page.AuthPage;
import com.test.page.MainPage;
import com.test_demo.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class MainTest extends BaseTest {
    private final static Logger LOG = LogManager.getLogger(MainTest.class);

    @Test(testName = "Login Tests Chrome", dataProvider = "testAuthDataProvider")
    public void firstTest(String login, String password, String expectedMessage) {

 //       String automationPracticeProperties = "src/main/resources/automation-practice.properties";
        String automationPracticeProperties = System.getProperty("automation-practice.cfg");
//        Properties properties = new Properties();
        Properties properties = new Properties();

        if (automationPracticeProperties != null) {
            try {
                properties.load(new FileReader(automationPracticeProperties));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String baseUrl = properties.getProperty("baseurl");
        driver.get(baseUrl);

        MainPage manePage = new MainPage(driver);
        AuthPage authPage = manePage.clickEnterLink();

        authPage.enterLogin(login);
        authPage.enterPassword(password);
        authPage.submit();

        String actualErrorMessage = authPage.getErrorMessage();

        try {
            assertEquals(actualErrorMessage, expectedMessage);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @DataProvider(name = "testAuthDataProvider")
    private Object[][] testAuthDataProvider() throws IOException {

        Object[][] authData = new Object[6][3];

        authData[0][0] = "some-login";
        authData[0][1] = "some-password";
        authData[0][2] = "Invalid email address.";

        authData[1][0] = "some-login";
        authData[1][1] = "<< your real password >>";
        authData[1][2] = "Invalid email address.";

        authData[2][0] = "<< your real email >>";
        authData[2][1] = "some-password";
        authData[2][2] = "Authentication failed.";

        authData[3][0] = "";
        authData[3][1] = "";
        authData[3][2] = "An email address required.";

        authData[4][0] = "<< your real email >>";
        authData[4][1] = "";
        authData[4][2] = "Password is required.";

        authData[5][0] = "";
        authData[5][1] = "<< your real password >>";
        authData[5][2] = "An email address required.";

        return authData;
    }

 /*
    @DataProvider(name = "testAuthDataProvider")
    private Object[][] testAuthDataProvider() throws IOException {

        String pathData = "src/data/automation-practice.xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(pathData));
An email address required.
t[sheet.getLastRowNum()][3];
//      System.out.println(sheet.getLastRowNum());
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow parRow = sheet.getRow(i);
            authData[i - 1][0] = (parRow.getCell(0) == null) ? "" : parRow.getCell(0).getStringCellValue();
            authData[i - 1][1] = (parRow.getCell(1) == null) ? "" : parRow.getCell(1).getStringCellValue();
            authData[i - 1][2] = parRow.getCell(2).getStringCellValue();
        }

        return authData;
    }

  */

}
