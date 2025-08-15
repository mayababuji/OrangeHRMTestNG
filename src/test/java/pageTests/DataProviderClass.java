package pageTests;

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
	  @DataProvider(name="SearchEmpName")
	  public Object[][] searchEmpName(){
		  return ExcelReader.getData("/Users/maya/eclipse-workspace/OrangeHRMTestNG/src/test/resources/LoginData.xlsx", "Sheet5");
	  }
	  @DataProvider(name="LoginAsNewEmployee")
	  public Object [][] loginAsNewEmp(){
		  Object[][] newEmpCreds = registerUserAsAdmin();
		  Object[][] data = new Object[newEmpCreds.length][2];
		  for (int i = 0; i < newEmpCreds.length; i++) {
		        data[i][0] = newEmpCreds[i][1].toString(); // username
		        data[i][1] = newEmpCreds[i][2].toString(); // password
		    }
		    return data;
		}
	  @DataProvider(name="UpdateEmpPersonalDetails")
	  public Object[][] updateNewEmpPersonalDetails(){
		  return ExcelReader.getData("/Users/maya/eclipse-workspace/OrangeHRMTestNG/src/test/resources/LoginData.xlsx", "Sheet4");
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
	  
	  public Map<String,String> getNewEmpCredentials(){
		  Object[][] newEmpCreds = registerUserAsAdmin();
		  for(Object[] row:newEmpCreds) {
			  Map<String,String> mapEmpLoginValues = new HashMap<>();
			  System.out.println(row[0].toString());
			  mapEmpLoginValues.put("NewEmpUsername", row[1].toString());
			  mapEmpLoginValues.put("NewEmpPassword", row[2].toString());
			  System.out.println(mapEmpLoginValues);
			  return mapEmpLoginValues;
			  
			  
		  }
		return null;
	  }
	  
	  public static void main(String[] args) {
		  DataProviderClass obj = new DataProviderClass();
//Object[][] employeeDetails = obj.registerUserAsAdmin();
//	  System.out.println(employeeDetails);
//	  for (Object[] row:employeeDetails) {
//		  for(Object cell:row) {
//			  System.out.println(cell+"\t");
//		  }
//		  System.out.println();
//	  }
		  
		  
//		  Map<String, String> creds = obj.getCredentialsByType("Valid");
//		  String username = creds.get("Username");
//	        String password = creds.get("Password");
//	        System.out.println(username);
//	        System.out.println(password);
		  
//		Object[][] loginAsNewEmpusername = obj.loginAsNewEmp();
//		  System.out.println(loginAsNewEmpusername);
//		  for (Object[] row:loginAsNewEmpusername) {
//			  for(Object cell:row) {
//				  System.out.println(cell+"\t");
//			  }
//			  System.out.println();
//		  }
//		  Map<String,String> empCreds = obj.getNewEmpCredentials();
//		  String empusername = empCreds.get("NewEmpUsername");
//		  String emppassword = empCreds.get("NewEmpPassword");
//		  System.out.println(empusername);
//		  System.out.println(emppassword);
		  
//		Object[][] employeeDetails = obj.updateNewEmpPersonalDetails();
//		  System.out.println(employeeDetails);
//		  for (Object[] row:employeeDetails) {
//			  for(Object cell:row) {
//				  System.out.println(cell+"\t");
//			  }
//			  System.out.println();
//		  }
		  
	}
}
