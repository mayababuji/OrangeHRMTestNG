package base;

import org.testng.annotations.AfterMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class BaseTest {
    public ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        }

        getDriver().manage().window().maximize();
        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod

    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }
}
