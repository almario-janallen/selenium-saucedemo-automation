package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private final WebDriver driver;

    //static locators
    private final By header = By.className("title");
    private final By shoppingCartLink = By.className("shopping_cart_link");

    //constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }


    private By productName(String itemName) {
        return By.xpath("//div[@class='inventory_item_name' and text()='" + itemName + "']");
    }

    private By productPrice(String itemName) {
        return By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']");
    }

    private By productDescription(String itemName) {
        return By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_desc']");
    }

    private By addToCartButton(String itemName) {
        return By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button");
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
}
