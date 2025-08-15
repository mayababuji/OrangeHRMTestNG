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
import pages.LoginPage;
import pages.UpdateEmployeeDetailsPage;
import utils.LoggerLoad;
@Epic("UpdateEmployee Module")
@Listeners(MyTestListener.class)
public class UpdateEmployeeDetailsTest extends BaseTest {
	 LoginPage loginPage;
	 UpdateEmployeeDetailsPage updateEmp;
	 DataProviderClass dataProvider;

@BeforeMethod
public void  loginAsNewEmp() {
	dataProvider = new DataProviderClass();
	loginPage = new LoginPage(getDriver());
	updateEmp = new UpdateEmployeeDetailsPage(getDriver());
	Map<String,String> newEmpCreds = dataProvider.getNewEmpCredentials();
	String newEmpUsername = newEmpCreds.get("NewEmpUsername");
	
	String newEmpPassword = newEmpCreds.get("NewEmpPassword");
	
	 LoggerLoad.info("==> Starting Before Method for login as employee to update employee details with credentials"+newEmpUsername);
	loginPage.login(newEmpUsername, newEmpPassword);
}
@Story("Update employee driver's license")
@Test(dataProvider="UpdateEmpPersonalDetails",dataProviderClass=DataProviderClass.class)
public void testUpdateDriverLicense(String driverlicense,String LicenseExpiryDate,String Nationality) {
	String drivingLicenseStr = driverlicense.toString();
	  LoggerLoad.debug("Updating Driver's License to: " + driverlicense);
updateEmp.clickOnMyInfo();

	String actualUpdatedDriverLicense = updateEmp.updateDriverLicense(drivingLicenseStr);
	   LoggerLoad.debug("Actual updated Driver's License: " + actualUpdatedDriverLicense);
	Assert.assertEquals(actualUpdatedDriverLicense, drivingLicenseStr,"Driver's License not updated correctly!");
	LoggerLoad.info("=== Completed testUpdateDriverLicense ===");
	
	
}
@Story("Update employee license expiry date")
@Test(dataProvider="UpdateEmpPersonalDetails",dataProviderClass=DataProviderClass.class)
public void testUpdateLicenseExpiryDate(String driverlicense,String LicenseExpiryDate,String Nationality) {
	updateEmp.clickOnMyInfo();
    LoggerLoad.debug("Setting License Expiry Date to: " + LicenseExpiryDate);
	String actualUpdatedLicenseExpDate = updateEmp.setLicenseExpiryDate(LicenseExpiryDate);
	 LoggerLoad.debug("Actual updated License Expiry Date: " + actualUpdatedLicenseExpDate);
	Assert.assertEquals(actualUpdatedLicenseExpDate, LicenseExpiryDate,"License expiry date not updated correctly!");
    LoggerLoad.info("=== Completed testUpdateLicenseExpiryDate ===");
}
@Story("Update employee nationality")
@Test(dataProvider="UpdateEmpPersonalDetails",dataProviderClass=DataProviderClass.class)

public void testUpdateNationality(String driverlicense,String LicenseExpiryDate,String Nationality) {
	updateEmp.clickOnMyInfo();
	LoggerLoad.debug("Updating Nationality to: " + Nationality);
	String actualUpdatedNationality = updateEmp.setNationality(Nationality);
	  LoggerLoad.debug("Actual updated Nationality: " + actualUpdatedNationality);
	Assert.assertEquals(actualUpdatedNationality, Nationality," Nationality not updated!");
    LoggerLoad.info("=== Completed testUpdateNationality ===");
}
}
