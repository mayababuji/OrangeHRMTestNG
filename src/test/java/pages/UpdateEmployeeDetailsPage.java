package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateEmployeeDetailsPage {
	 WebDriver driver;
WebDriverWait wait;
public UpdateEmployeeDetailsPage(WebDriver driver) {
	this.driver = driver;
	this.wait = new WebDriverWait(driver,Duration.ofSeconds(60));
	
}
private By myInfo = By.xpath("//span[normalize-space()='My Info']");
private By driverLicense = By.xpath("//label[normalize-space()=\"Driver's License Number\"]/following::input[1]");
private By expiryDateField = By.xpath("//label[normalize-space()='License Expiry Date']/following::input[1]");
private By nationality = By.xpath("(//div[normalize-space()='-- Select --'])[4]/following::i[1]"); //Select the 4th matching <div>
private By nationalityDropDown = By.xpath("//div[contains(@class,'oxd-select-dropdown')]");

public void clickOnMyInfo() {
	WebElement myInfoField = wait.until(ExpectedConditions.visibilityOfElementLocated(myInfo));
	myInfoField.click();
}
public  String updateDriverLicense(String drivLicense) {
	WebElement driverLicenseField = wait.until(ExpectedConditions.elementToBeClickable(driverLicense));
	driverLicenseField.sendKeys(drivLicense);
	 wait.until(ExpectedConditions.attributeContains(driverLicenseField, "value", drivLicense));
	 String updatedDriverLicenseValue = driver.findElement(driverLicense).getAttribute("value");
	 return updatedDriverLicenseValue;
}

public String setLicenseExpiryDate(String date) {
    WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(expiryDateField));
    dateField.clear();
    dateField.sendKeys(date); // e.g. "2025-08-12"
    wait.until(ExpectedConditions.attributeContains(expiryDateField, "value", date));
    String updatedDateValue = driver.findElement(expiryDateField).getAttribute("value");
    System.out.println(updatedDateValue);
    return updatedDateValue;
}

public String setNationality(String selectedNationality)  {
    Actions actions = new Actions(driver);
    

    // Click the dropdown icon

    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(nationality));
    dropdown.click();

    // Wait for dropdown container to be visible 

    WebElement dropdownContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(nationalityDropDown));

    // Find all options inside dropdown
    List<WebElement> options = dropdownContainer.findElements(By.xpath(".//div"));

    boolean indianFound = false;
    int indianIndex = -1;

    // Loop to check if "Indian" exists and remember its position
    for (int i = 0; i < options.size(); i++) {
        String text = options.get(i).getText().trim();
        if (text.equalsIgnoreCase(selectedNationality)) {
            indianFound = true;
            indianIndex = i-1;
            break;
        }
    }

    if (indianFound) {
        // Now send arrow down keys to reach the option at indianIndex
        for (int i = 0; i <= indianIndex; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
  
        }
        // Press Enter to select
        actions.sendKeys(Keys.ENTER).perform();
        System.out.println(selectedNationality+"option selected");
    } else {
        throw new RuntimeException(nationality+" option NOT found in dropdown");
    }
     By expectedSelectedNationality = By.xpath("//div[contains(text(),'" + selectedNationality + "')]");
     WebElement actualSelectNationalityelement = wait.until(ExpectedConditions.visibilityOfElementLocated(expectedSelectedNationality));
     String actualValue = actualSelectNationalityelement.getText().trim();
     System.out.println(actualValue);
     return actualValue;
}



}
