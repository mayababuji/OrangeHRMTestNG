package pageTests;

import org.testng.annotations.Test;
import base.BaseTest;
//import pageTests.DataProviderClass;
import listeners.RetryAnalyzer;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import pages.LoginPage;
import utils.LoggerLoad;

import utils.ExcelReader;
import io.qameta.allure.*;
@Epic("Login Module")
@Feature(" Login Test with valid and invalid scenarios")
public class LoginTest extends BaseTest {

	@Story("Valid and invalid login data combinations")
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviderClass.class)
    public void testLogin(String username, String password, String expectedError, String errorType, String testType) {
    	LoggerLoad.info("==> Starting testLogin for user: " + username);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        switch (errorType.toLowerCase()) {
            case "popup":
            	 LoggerLoad.debug("Checking login with invalid username error validation");
            	//pop up message Invalid credentials
                String popupMsg = loginPage.getPopupErrorMessage();
                Assert.assertEquals(popupMsg, expectedError);
                LoggerLoad.info("Login with invalid username error validation passed");
                break;

            case "inline-user":
            	 LoggerLoad.debug("Checking login with blank username error validation");
            	//blank username case
                String userError = loginPage.getInlineErrorMessage();
                Assert.assertEquals(userError, expectedError);
                LoggerLoad.info("Login with blank username error validation passed");
                break;

            case "inline-pass":
            	 LoggerLoad.debug("Checking login with blank password error validation");
            	//blank password case
                String passError = loginPage.getInlineErrorMessage();
                Assert.assertEquals(passError, expectedError);
                LoggerLoad.info("Login with blank password error validation passed");
                break;

            case "inline-both":
            	 LoggerLoad.debug("Checking login with both  blank username and password error validation");
            	//blank username and password case
                Assert.assertEquals(loginPage.getInlineErrorMessage(), expectedError);
                LoggerLoad.info("Login with both blank username and password error validation passed");
                break;

            case "none":
            	 LoggerLoad.debug("Checking login with valid username and password validation");
            	//Gets the current URL of the browser after login Checks if the URL contains the word "dashboard" 
            	//(which indicates login success in OrangeHRM) if the test failes "Login faield message is displayed
                Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"), "Login failed");
                LoggerLoad.info("Login with valid username and password  validation passed");
                break;

            default:
            	LoggerLoad.error("Invalid errorType in test data: " + errorType);
                Assert.fail("Invalid errorType in test data: " + errorType);
        }
    }
	@Story(" Login Test with invalid url")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLogininvalidurl() {
        LoggerLoad.info("Running testLogininvalidurl");

        try {
            getDriver().get("https://opensource-demo.orangeinvalidlive.com/");
            Assert.fail("Page loaded successfully, but it was expected to FAIL");
        } catch (Exception e) {
            LoggerLoad.info("URL failed to resolve as expected: " + e.getMessage());
            throw new RuntimeException("Retryable failure", e); // Wrap it so retry analyzer catches it
        }
    }

}
