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

    //constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    public boolean isOnInventoryPage() {
//        return driver.getCurrentUrl().contains("inventory.html") && driver.findElement(By.id("inventory_container")).isDisplayed();
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


    // 🔹 Methods that accept dynamic itemName (manual or Excel-driven)
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

    private final By inventoryNames = By.className("inventory_item_name");

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
}
