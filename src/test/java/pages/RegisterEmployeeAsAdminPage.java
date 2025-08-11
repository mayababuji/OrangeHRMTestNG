package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;

public class RegisterEmployeeAsAdminPage {
	 WebDriver driver;
	    WebDriverWait wait;
	
	public RegisterEmployeeAsAdminPage(WebDriver driver){
		 this.driver = driver;
         this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
	}
	private By admin = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Admin']");
	private By add = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	private By userRole = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]");
	private By status = By.xpath("//div[3]//div[1]//div[2]//div[1]//div[1]//div[1]");
	private By  userName = By.xpath("//div[@class='oxd-form-row']//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");
	private By password = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']");
	private By confirmPassword = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']");
	private By saveButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	private By adminDropDown = By.xpath("//div[@role='option']//span[normalize-space()='Admin']");
	private By empNameDropDown = By.xpath("//input[@placeholder='Type for hints...']");
	public void clickOnAdmin() {
		WebElement adminField = wait.until(ExpectedConditions.visibilityOfElementLocated(admin));
		adminField.click();
		
	}
	
	public void clickOnAdd() {
		WebElement addField = wait.until(ExpectedConditions.visibilityOfElementLocated(add));
		addField.click();
	}
	
	public void selectUserRole() {
		WebElement userRoleDropDown = wait.until(ExpectedConditions.elementToBeClickable(userRole));
		userRoleDropDown.click();
		 // 2. Select "Admin" from the dropdown
        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(adminDropDown));
        adminOption.click();
		
	}
	public void enterEmployeeName(String empName) {
	     WebElement empNameField = wait.until(ExpectedConditions.elementToBeClickable(empNameDropDown));
	         // 2. Type "maya" into the field
	       
	         Actions actions = new Actions(driver);
	         actions.sendKeys(empNameField, empName).perform();
	         try {
	 			Thread.sleep(2000);
	 		} catch (InterruptedException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	       

	       empNameField.click();
	     
	   
	         empNameField.sendKeys(Keys.ARROW_DOWN);
	         empNameField.sendKeys(Keys.ENTER);
		
	}
	public void selectStatus() {
		 WebElement statusDropDown = wait.until(ExpectedConditions.elementToBeClickable(status));
	        statusDropDown.click();

	        statusDropDown.sendKeys(Keys.ARROW_DOWN);
	        statusDropDown.sendKeys(Keys.ENTER);
		
	}

	
	public void enterUsername(String username) {
		WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
		userNameField.sendKeys(username);
		
	}
	public void enterPassword(String Password) {
		WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
		passwordField.sendKeys(Password);
	}
	public void  confirmPassword(String ConfirmPassword) {
		WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword));
		confirmPasswordField.sendKeys(ConfirmPassword);
	}
	
	public void clickOnSave() {
		WebElement saveField = wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));
		saveField.click();
		
	}
	public void waitForViewSystemUsersUrl(String successMessage) {
	    wait.until(ExpectedConditions.urlContains(successMessage));
	}
		  
}
