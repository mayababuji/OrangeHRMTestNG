package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginAsNewEmployeeTest extends BaseTest {

	@Test(dataProvider="LoginAsNewEmployee",dataProviderClass = DataProviderClass.class)
public void testloginAsNewEmployee(String NewEmpUsername, String NewEmpPassword) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(NewEmpUsername, NewEmpPassword);
		System.out.println(getDriver().getCurrentUrl());
		loginPage.waitForExpectedUrl("dashboard");
		Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"), "Failed to login as new employee");
	
}
}
