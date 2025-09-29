package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //static locators
    private final By header = By.className("title");
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By inventoryNames = By.className("inventory_item_name");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By resetAppState = By.id("reset_sidebar_link");

    //constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    public boolean isOnInventoryPage() {
        return wait.until(ExpectedConditions.urlContains("inventory.html")) && driver.findElement(By.id("inventory_container")).isDisplayed();
    }

    private By productName(String itemName) {
        return By.xpath("//div[contains(@class,'inventory_item_name') and text()='" + itemName + "']");
    }

    private By productPrice(String itemName) {
        return By.xpath("//div[contains(@class,'inventory_item_name') and normalize-space(text())='"
                + itemName + "']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']");
    }

    private By productDescription(String itemName) {
        return By.xpath("//div[contains(@class,'inventory_item_name') and normalize-space(text())='"
                + itemName + "']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_desc']");
    }

    private By addToCartButton(String itemName) {
        return By.xpath("//div[contains(@class,'inventory_item_name') and normalize-space(text())='"
                + itemName + "']/ancestor::div[@class='inventory_item']//button");
    }

    // Methods that accept dynamic itemName (manual or Excel-driven)
    public String getInventoryName(String itemName) {
        return driver.findElement(productName(itemName)).getText();
    }

    public String getInventoryPrice(String itemName) {
        return driver.findElement(productPrice(itemName)).getText();
    }

    public String getInventoryDescription(String itemName) {
        return driver.findElement(productDescription(itemName)).getText();
    }

    public void addToCart(String itemName) {
        driver.findElement(addToCartButton(itemName)).click();
    }

    public void goToShoppingCart() {
        driver.findElement(shoppingCartLink).click();
    }


    public List<String> getInventoryItems() {
        List<WebElement> items = driver.findElements(inventoryNames);
        List<String> itemNames = new ArrayList<>();
        for(WebElement item : items) {
            itemNames.add(item.getText().trim());
        }
        return itemNames;
    }

    public void goToProduct(String itemName) {
        driver.findElement(productName(itemName)).click();
    }

    public int getCartBadgeCount() {
        List<WebElement> badge = driver.findElements(cartBadge);
        if (badge.isEmpty()) {
            return 0; // No badge = empty cart
        }
        return Integer.parseInt(badge.get(0).getText().trim());
    }

    public void resetAppState() {
        driver.findElement(menuButton).click();
        WebElement resetAppStateLink = wait.until(ExpectedConditions.visibilityOf(driver.findElement(resetAppState)));
        resetAppStateLink.click();
    }
}
