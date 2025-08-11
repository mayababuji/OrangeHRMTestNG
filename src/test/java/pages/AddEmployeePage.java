package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeePage {
	//// Class-level field: accessible in all methods
	 WebDriver driver;
	    WebDriverWait wait;
	    
	    public AddEmployeePage( WebDriver driver) {
//	    	/// Assign the parameter to the class field
	    	 this.driver = driver;
	         this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
private By PIM = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']");
private By addEmployee=By.xpath("//a[normalize-space()='Add Employee']");
private By firstName = By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-firstname']");
private By middleName = By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-middlename']");
private By lastName = By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-lastname']");
private By saveEmployee = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
private By successToast = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

public void clickOnPIM() {
	WebElement PIMFeild = wait.until(ExpectedConditions.elementToBeClickable(PIM));
	PIMFeild.click();
}
public void clickOnAddEmployess() {
	WebElement addEmployeeField = wait.until(ExpectedConditions.elementToBeClickable(addEmployee));
	addEmployeeField.click();
}
public void createEmployees(String username,String middlename,String lastname) {
	WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
	firstNameField.sendKeys(username);
	WebElement middleNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(middleName));
	middleNameField.sendKeys(middlename);
	WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
	lastNameField.sendKeys(lastname);
	WebElement saveEmployeeField = wait.until(ExpectedConditions.visibilityOfElementLocated(saveEmployee));
	saveEmployeeField.click();
	
}
public String getSuccessMessage() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    return driver.findElement(successToast).getText();
}
}
