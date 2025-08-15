package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchEmployeePage {
	 WebDriver driver;
WebDriverWait wait;
public SearchEmployeePage(WebDriver driver) {
	this.driver = driver;
	this.wait = new WebDriverWait(driver,Duration.ofSeconds(60));
	
}
private By employesName = By.xpath("//label[normalize-space()='Employee Name']/following::input[1]");
private By searchButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
private By recordsResults = By.xpath("//span[@class='oxd-text oxd-text--span']");
public void searchEmployeeName(String empName) {
	WebElement employesNameField = wait.until(ExpectedConditions.elementToBeClickable(employesName));
	employesNameField.sendKeys(empName);
}
public void clickOnSearch() {
	WebElement searchButtonField = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
	searchButtonField.click();
}
public String getSearchRecords() {
	WebElement recordsResultsField = wait.until(ExpectedConditions.visibilityOfElementLocated(recordsResults));
	String recordResult = recordsResultsField.getText();
	System.out.println(recordsResultsField.getText());
	return recordResult;
}

}
