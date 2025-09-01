package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InventoryItemPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // constructor
    public InventoryItemPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // static locators
    private final By backToProductsButton = By.id("back-to-products");
    private final By addToCartButton = By.id("add-to-cart");

    private final By inventoryItemName = By.cssSelector("[data-test='inventory-item-name']");
    private final By inventoryItemDescription = By.cssSelector("[data-test='inventory-item-desc']");
    private final By inventoryItemPrice = By.cssSelector("[data-test='inventory-item-price']");

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getInventoryItemName() {
        return driver.findElement(inventoryItemName).getText();
    }

    public String getInventoryItemDescription() {
        return driver.findElement(inventoryItemDescription).getText();
    }

    public String getInventoryItemPrice() {
        return driver.findElement(inventoryItemPrice).getText();
    }

    public void backToProducts() {
        driver.findElement(backToProductsButton).click();
    }
}
