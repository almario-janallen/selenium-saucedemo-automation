package tests;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ScreenshotUtil;
import utils.WebDriverFactory;
import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(Method method) {
        driver = WebDriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        if(!method.getName().equals("testLoginScenarios")){
            Cookie session = new Cookie.Builder("session-username", "standard_user")
                    .domain("www.saucedemo.com")
                    .path("/")
                    .isHttpOnly(false)
                    .isSecure(false)
                    .build();

            driver.manage().addCookie(session);
            driver.navigate().refresh();

        }

        // Navigate directly to the inventory page
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot first if test failed
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            ScreenshotUtil.captureScreenshot(driver, result.getName(), "failure");
        }

        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
