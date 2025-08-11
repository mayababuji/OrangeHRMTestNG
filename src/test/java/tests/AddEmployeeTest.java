package tests;
import org.testng.annotations.Test;
import base.BaseTest;

import java.util.Map;

import org.testng.Assert;

import pages.LoginPage;
import pages.AddEmployeePage;

import org.testng.annotations.BeforeMethod;
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
    Map<String, String> creds  = dataprovider.getCredentialsByType("Valid"); 
    String username = creds.get("Username");
    String password = creds.get("Password");
    loginPage.login(username, password);
   

   
}
@Test

	public void testClickOnPIM() {
	addEmpPage.clickOnPIM();
	  Assert.assertTrue(getDriver().getCurrentUrl().contains("pim"), "Click on PIM failed");
		
	}
@Test
public void testClickOnAddEmployee() {
	addEmpPage.clickOnPIM();
	addEmpPage.clickOnAddEmployess();
	Assert.assertTrue(getDriver().getCurrentUrl().contains("addEmployee"),"Cilck on Add Employee failed");
}
@Test(dataProvider="CreateEmployee",dataProviderClass=DataProviderClass.class)
public void testCreateEmployee(String FirstName,String MiddleName,String Lastname,String SuccessMessage) {
	addEmpPage.clickOnPIM();
	addEmpPage.clickOnAddEmployess();
	addEmpPage.createEmployees(FirstName,MiddleName,Lastname);
	String msg = addEmpPage.getSuccessMessage();
	Assert.assertTrue(msg.contains(SuccessMessage),"Success message not found");
	
}

}
