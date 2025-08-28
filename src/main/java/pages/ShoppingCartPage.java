package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ShoppingCartPage {
    private final WebDriver driver;

    // Constructor
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    //Static Locators
    private final By checkoutButton = By.xpath("//*[@id=\"checkout\"]");

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }
}
