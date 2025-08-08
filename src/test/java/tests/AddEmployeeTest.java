package tests;
import org.testng.annotations.Test;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import pages.LoginPage;
import pages.AddEmployeePage;
import utils.ExcelReader;
import org.testng.annotations.BeforeMethod;
public class AddEmployeeTest extends BaseTest {
	/* 1. Declare the variable (no object created yet)
	 * creates a reference variable at the class level so all your test methods can use addEmpPage
	 */
	
	 LoginPage loginPage;
	    AddEmployeePage addEmpPage;
	    
 @BeforeMethod
public void loginAsAdmin() {
	 /*  2. Initialize it with the current WebDrive, creates a real instance of AddEmployeePage with the current WebDriver.
	 Class fields are initialized very early, before TestNG calls  setup methods (BaseTest)
	 WebDriver isn’t created yet, so getDriver() returns null.
	To avoid this, only create page objects after WebDriver is ready, e.g., inside @BeforeMethod. */
	loginPage = new LoginPage(getDriver());
    addEmpPage = new AddEmployeePage(getDriver());
    loginPage.login("Admin", "admin123");

   
}
@Test

	public void testClickOnPIM() {
	addEmpPage.clickOnPIM();
	  Assert.assertTrue(getDriver().getCurrentUrl().contains("pim"), "Click on PIM failed");
	        
	
		
	}

}
