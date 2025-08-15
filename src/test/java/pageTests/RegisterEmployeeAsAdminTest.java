package pageTests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import listeners.MyTestListener;
import pages.AddEmployeePage;
import pages.LoginPage;
import pages.RegisterEmployeeAsAdminPage;
import utils.LoggerLoad;
@Epic("AddEmployee Module")
@Listeners(MyTestListener.class)
public class RegisterEmployeeAsAdminTest extends BaseTest {
	
	LoginPage loginPage;
	RegisterEmployeeAsAdminPage regEmpPage;
	   AddEmployeePage addEmpPage;
	
	@BeforeMethod
	public void loginAsAdmin() {
		DataProviderClass dataprovider = new DataProviderClass();
		 loginPage = new LoginPage(getDriver());
		 regEmpPage = new RegisterEmployeeAsAdminPage(getDriver());
		   addEmpPage = new AddEmployeePage(getDriver());
		Map<String, String> creds = dataprovider.getCredentialsByType("Valid");
	
		String username = creds.get("Username");
		String password = creds.get("Password");
		LoggerLoad.info("==> Starting Before Method for login as New Employee with username"+username);
		loginPage.login(username, password);
	}
	@Story("Register new user as admin")
	@Test(dataProvider="RegisterUserAsAdmin",dataProviderClass=DataProviderClass.class)
	
	public void testRegEmpAsAdmin(String employeeName,String userName,String password,String confirmPassword,String successMessage,String currentUrl) {
		 LoggerLoad.info("=== Starting testRegEmpAsAdmin for employee: " + employeeName + " ===");
		 LoggerLoad.debug("Clicking on Admin menu...");
		regEmpPage.clickOnAdmin();
		 LoggerLoad.debug("Clicking on Add button...");
		regEmpPage.clickOnAdd();
		  LoggerLoad.debug("Selecting user role...");
		regEmpPage.selectUserRole();
		 LoggerLoad.debug("Entering employee name: " + employeeName);
		regEmpPage.enterEmployeeName(employeeName);
		LoggerLoad.debug("Selecting status...");
		regEmpPage.selectStatus();
	    LoggerLoad.debug("Entering username: " + userName);
		regEmpPage.enterUsername(userName);
		 LoggerLoad.debug("Entering password...");
		regEmpPage.enterPassword(password);
		 LoggerLoad.debug("Confirming password...");
		regEmpPage.confirmPassword(password);
		regEmpPage.clickOnSave();
		String msg = addEmpPage.getSuccessMessage();
		 LoggerLoad.debug("Received success message: " + msg);
		Assert.assertTrue(msg.contains(successMessage),"Success message not found");
		 LoggerLoad.debug("Waiting for system users page URL: " + currentUrl);
		    String actualUrl = getDriver().getCurrentUrl();
		    LoggerLoad.debug("Current URL after registration: " + actualUrl);
		regEmpPage.waitForViewSystemUsersUrl(currentUrl);
		
		  Assert.assertTrue(getDriver().getCurrentUrl().contains(currentUrl), "Register employee admin failed");
		  LoggerLoad.info("=== Completed testRegEmpAsAdmin for employee: " + employeeName + " ===");
	}

}
