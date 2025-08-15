package pageTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import listeners.MyTestListener;
import pages.LoginPage;
import utils.LoggerLoad;
@Epic("AddEmployee Module")
@Listeners(MyTestListener.class)
public class LoginAsNewEmployeeTest extends BaseTest {
	@Story("Validate login as New Employee")
	@Test(dataProvider="LoginAsNewEmployee",dataProviderClass = DataProviderClass.class)
public void testloginAsNewEmployee(String NewEmpUsername, String NewEmpPassword) {
		LoginPage loginPage = new LoginPage(getDriver());
		 LoggerLoad.info("=== Logging in with new employee credentials: " + NewEmpUsername + " " + NewEmpPassword+ " ===");
		loginPage.login(NewEmpUsername, NewEmpPassword);
		System.out.println(getDriver().getCurrentUrl());
		 String currentUrl = getDriver().getCurrentUrl();
			LoggerLoad.debug("After clicking  on login as new employee, current URL is: " + currentUrl);
		loginPage.waitForExpectedUrl("dashboard");
		Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"), "Failed to login as new employee");
		LoggerLoad.info("Succesfully Logged in as new employee");
	
}
}
