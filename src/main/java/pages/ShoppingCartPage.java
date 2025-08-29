package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Constructor
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOnShoppingCartPage() {
//        return driver.getCurrentUrl().contains("cart.html") && driver.findElement(By.className("cart_list")).isDisplayed();
        return wait.until(ExpectedConditions.urlContains("cart.html")) && driver.findElement(By.className("cart_list")).isDisplayed();
    }


    //Static Locators
    private final By checkoutButton = By.id("checkout");

    public void clickCheckoutButton() {
        try{
            driver.findElement(checkoutButton).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
