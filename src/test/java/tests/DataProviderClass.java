package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import utils.ExcelReader;

public class DataProviderClass {
	  @DataProvider(name = "LoginData")
	    public Object[][] loginData() {
	        return ExcelReader.getData("/Users/maya/eclipse-workspace/OrangeHRMTestNG/src/test/resources/LoginData.xlsx", "Sheet1");
	    }
	  
	  @DataProvider(name = "ValidLoginData")
	    public Object[][] validLoginData() {
		  Object[][] creds = loginData(); 
		  return new Object[][] {
		        { creds[1][0], creds[1][1] }  // one row: username, password
		    };
	    }
	  @DataProvider(name="CreateEmployee")
	  public Object[][] createEmployee(){
		  
		  return ExcelReader.getData("/Users/maya/eclipse-workspace/OrangeHRMTestNG/src/test/resources/LoginData.xlsx", "Sheet2");
	  }
	  @DataProvider(name="RegisterUserAsAdmin")
	  public Object[][] registerUserAsAdmin(){
		  return ExcelReader.getData("/Users/maya/eclipse-workspace/OrangeHRMTestNG/src/test/resources/LoginData.xlsx", "Sheet3");
	  }
	  
	  public Map<String, String> getCredentialsByType(String type) {
	        Object[][] creds = loginData();

	        for (Object[] row : creds) {
	            // column 4 contains  TestType, e.g. "Valid" or "Invalid"
	            if (row.length > 2 && row[4].toString().equalsIgnoreCase(type)) {
	                Map<String, String> map = new HashMap<>();
	                map.put("Username", row[0].toString());
	                map.put("Password", row[1].toString());
	                return map;
	            }
	        }
	        return null; // or throw exception if not found
	    }
	  
	  public static void main(String[] args) {
		  DataProviderClass obj = new DataProviderClass();
Object[][] employeeDetails = obj.registerUserAsAdmin();
	  System.out.println(employeeDetails);
	  for (Object[] row:employeeDetails) {
		  for(Object cell:row) {
			  System.out.println(cell+"\t");
		  }
		  System.out.println();
	  }
//		  Map<String, String> creds = obj.getCredentialsByType("Valid");
//		  String username = creds.get("Username");
//	        String password = creds.get("Password");
//	        System.out.println(username);
//	        System.out.println(password);
	        
		  
	}
}
