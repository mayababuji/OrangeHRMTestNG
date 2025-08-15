package pageTests;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import listeners.MyTestListener;
import base.BaseTest;

import java.util.Map;

import org.testng.Assert;

import pages.LoginPage;
import utils.LoggerLoad;
import pages.AddEmployeePage;
import io.qameta.allure.*;

import org.testng.annotations.BeforeMethod;
@Epic("AddEmployee Module")
@Listeners(MyTestListener.class)
public class AddEmployeeTest extends BaseTest {
	/* 1. Declare the variable (no object created yet)
	 * creates a reference variable at the class level so all your test methods can use addEmpPage
	 */
	
	 LoginPage loginPage;
	    AddEmployeePage addEmpPage;
	    
 @BeforeMethod
public void loginAsAdmin() {
	 /*  2. Initialize it with the current WebDriver, creates a real instance of AddEmployeePage with the current WebDriver.
	 Class fields are initialized very early, before TestNG calls  setup methods (BaseTest)
	 WebDriver isn’t created yet, so getDriver() returns null.
	To avoid this, only create page objects after WebDriver is ready, e.g., inside @BeforeMethod. */
	 DataProviderClass dataprovider = new DataProviderClass();
	loginPage = new LoginPage(getDriver());
    addEmpPage = new AddEmployeePage(getDriver());
    //login in with valid credentials
    LoggerLoad.info("==> Starting Before Method for login as Admin for Add Employee Test");
    Map<String, String> creds  = dataprovider.getCredentialsByType("Valid"); 
    String username = creds.get("Username");
    String password = creds.get("Password");
    loginPage.login(username, password);
   

   
}
	@Story("Click in PIM")
@Test

	public void testClickOnPIM() {
		  LoggerLoad.debug("Clicking on PIM menu item...");
	addEmpPage.clickOnPIM();
	 String currentUrl = getDriver().getCurrentUrl();
	LoggerLoad.debug("After clicking PIM, current URL is: " + currentUrl);
	  Assert.assertTrue(getDriver().getCurrentUrl().contains("pim"), "Click on PIM failed");
	  LoggerLoad.info("Succesfully clicked on PIM menu");
		
	}
	@Story("Add new employee")
@Test
public void testClickOnAddEmployee() {
		 LoggerLoad.debug("Clicking on Add Employee menu item...");
	addEmpPage.clickOnPIM();
	addEmpPage.clickOnAddEmployess();
	 String currentUrl = getDriver().getCurrentUrl();
		LoggerLoad.debug("After clicking  Add Employee, current URL is: " + currentUrl);
	Assert.assertTrue(getDriver().getCurrentUrl().contains("addEmployee"),"Cilck on Add Employee failed");
	LoggerLoad.info("Succesfully clicked on Add Employee menu");
}
	@Story("Create new employee")
@Test(dataProvider="CreateEmployee",dataProviderClass=DataProviderClass.class)
public void testCreateEmployee(String FirstName,String MiddleName,String Lastname,String SuccessMessage) {
		 LoggerLoad.info("=== Starting testCreateEmployee for: " + FirstName + " " + MiddleName + " " + Lastname + " ===");
		 LoggerLoad.debug("Clicking on PIM menu...");
	addEmpPage.clickOnPIM();
	LoggerLoad.debug("Clicking on Add Employee button...");
	addEmpPage.clickOnAddEmployess();
	LoggerLoad.debug("Filling employee details: FirstName=" + FirstName + ", MiddleName=" + MiddleName + ", LastName=" + Lastname);
	addEmpPage.createEmployees(FirstName,MiddleName,Lastname);
	String msg = addEmpPage.getSuccessMessage();
	LoggerLoad.debug("Received success message: " + msg);
	Assert.assertTrue(msg.contains(SuccessMessage),"Success message not found");
	 LoggerLoad.info("=== testCreateEmployee completed successfully for: " + FirstName + " " + MiddleName + " " + Lastname + " ===");
	
}

}
