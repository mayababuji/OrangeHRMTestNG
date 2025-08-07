package tests;

import org.testng.annotations.Test;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import pages.LoginPage;
import utils.ExcelReader;

public class LoginTest extends BaseTest {

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return ExcelReader.getData("/Users/maya/eclipse-workspace/OrangeHRMTestNG/src/test/resources/LoginData.xlsx", "Sheet1");
    }

    @Test(dataProvider = "LoginData")
    public void testLogin(String username, String password, String expectedError, String errorType) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        switch (errorType.toLowerCase()) {
            case "popup":
            	//pop up message Invalid credentials
                String popupMsg = loginPage.getPopupErrorMessage();
                Assert.assertEquals(popupMsg, expectedError);
                break;

            case "inline-user":
            	//blank username case
                String userError = loginPage.getInlineErrorMessage();
                Assert.assertEquals(userError, expectedError);
                break;

            case "inline-pass":
            	//blank password case
                String passError = loginPage.getInlineErrorMessage();
                Assert.assertEquals(passError, expectedError);
                break;

            case "inline-both":
            	//blank username and password case
                Assert.assertEquals(loginPage.getInlineErrorMessage(), expectedError);
                break;

            case "none":
            	//Gets the current URL of the browser after login Checks if the URL contains the word "dashboard" 
            	//(which indicates login success in OrangeHRM) if the test failes "Login faield message is displayed
                Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"), "Login failed");
                break;

            default:
                Assert.fail("Invalid errorType in test data: " + errorType);
        }
    }

}
