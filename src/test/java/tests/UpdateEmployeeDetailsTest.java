package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;

import pages.LoginPage;
import pages.UpdateEmployeeDetailsPage;

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
	System.out.println(newEmpUsername);
	System.out.println(newEmpPassword);
	loginPage.login(newEmpUsername, newEmpPassword);
}
@Test(dataProvider="UpdateEmpPersonalDetails",dataProviderClass=DataProviderClass.class)
public void testUpdateDriverLicense(String driverlicense,String LicenseExpiryDate,String Nationality) {
	String drivingLicenseStr = driverlicense.toString();
updateEmp.clickOnMyInfo();
	String actualUpdatedDriverLicense = updateEmp.updateDriverLicense(drivingLicenseStr);
	Assert.assertEquals(actualUpdatedDriverLicense, drivingLicenseStr,"Driver's License not updated correctly!");
	
}
@Test(dataProvider="UpdateEmpPersonalDetails",dataProviderClass=DataProviderClass.class)
public void testUpdateLicenseExpiryDate(String driverlicense,String LicenseExpiryDate,String Nationality) {
	updateEmp.clickOnMyInfo();
	String actualUpdatedLicenseExpDate = updateEmp.setLicenseExpiryDate(LicenseExpiryDate);
	Assert.assertEquals(actualUpdatedLicenseExpDate, LicenseExpiryDate,"License expiry date not updated correctly!");
}
@Test(dataProvider="UpdateEmpPersonalDetails",dataProviderClass=DataProviderClass.class)

public void testUpdateNationality(String driverlicense,String LicenseExpiryDate,String Nationality) {
	updateEmp.clickOnMyInfo();
	String actualUpdatedNationality = updateEmp.setNationality(Nationality);
	Assert.assertEquals(actualUpdatedNationality, Nationality," Nationality not updated!");
}
}
