package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        WebDriver webDriver = new ChromeDriver();
        driver.set(webDriver);
        return driver.get();
    }

    public static void quitDriver() {
        if(driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
