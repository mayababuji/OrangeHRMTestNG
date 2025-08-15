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
import pages.SearchEmployeePage;
import pages.UpdateEmployeeDetailsPage;
import utils.LoggerLoad;
@Epic("Search employee by name Module")
@Listeners(MyTestListener.class)
public class SearchEmployeeTest extends BaseTest {
	 AddEmployeePage addEmpPage;
	 LoginPage loginPage;
	 DataProviderClass dataProvider;
	 SearchEmployeePage searchEmp;
	 @BeforeMethod
	 public void  loginAsNewEmp() {
	 	dataProvider = new DataProviderClass();
	 	loginPage = new LoginPage(getDriver());
	 	searchEmp = new SearchEmployeePage(getDriver());
	    addEmpPage = new AddEmployeePage(getDriver());
	 	Map<String,String> newEmpCreds = dataProvider.getNewEmpCredentials();
	 	String newEmpUsername = newEmpCreds.get("NewEmpUsername");
	 	
	 	String newEmpPassword = newEmpCreds.get("NewEmpPassword");
	 	 LoggerLoad.info("==> Starting Before Method for login as employee to search employee by name with credentials"+newEmpUsername);
	 	loginPage.login(newEmpUsername, newEmpPassword);
	 }
		@Story("Search employee by name")
	 @Test(dataProvider="SearchEmpName",dataProviderClass=DataProviderClass.class)

		public void testClickSearchEmp(String Name,String ExpectedRecordResult) {

		    LoggerLoad.info("=== Starting testClickSearchEmp for employee: " + Name + " ===");
		addEmpPage.clickOnPIM();
	    LoggerLoad.debug("Entering employee name to search: " + Name);
		searchEmp.searchEmployeeName(Name);
		 LoggerLoad.debug("Clicking on Search button...");
		searchEmp.clickOnSearch();
		String actualRecordString = searchEmp.getSearchRecords();
		LoggerLoad.debug("Actual search result: " + actualRecordString + 
                " | Expected: " + ExpectedRecordResult);
//		String expectedRecordString = "(1) Record Found";
//		System.out.println(Name);
//		System.out.println(ExpectedRecordResult);
		Assert.assertEquals(actualRecordString, ExpectedRecordResult,"No records for employee name found");
		 LoggerLoad.info("=== Completed testClickSearchEmp for employee: " + Name + " ===");
		 
			
		}
	 

}
