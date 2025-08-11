package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AddEmployeePage;
import pages.LoginPage;
import pages.RegisterEmployeeAsAdminPage;

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
		loginPage.login(username, password);
	}
	@Test(dataProvider="RegisterUserAsAdmin",dataProviderClass=DataProviderClass.class)
	
	public void testRegEmpAsAdmin(String employeeName,String userName,String password,String confirmPassword,String successMessage,String currentUrl) {
		regEmpPage.clickOnAdmin();
		regEmpPage.clickOnAdd();
		regEmpPage.selectUserRole();
		regEmpPage.enterEmployeeName(employeeName);
		regEmpPage.selectStatus();
		regEmpPage.enterUsername(userName);
		regEmpPage.enterPassword(password);
		regEmpPage.confirmPassword(confirmPassword);
		regEmpPage.clickOnSave();
		String msg = addEmpPage.getSuccessMessage();
		Assert.assertTrue(msg.contains(successMessage),"Success message not found");
		
		regEmpPage.waitForViewSystemUsersUrl(currentUrl);
		
		  Assert.assertTrue(getDriver().getCurrentUrl().contains(currentUrl), "Register employee admin failed");
	}

}
