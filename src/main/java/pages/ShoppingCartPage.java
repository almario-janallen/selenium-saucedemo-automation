package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartItems = By.className("cart_item");
    private final By itemName = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");

    // Constructor
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOnShoppingCartPage() {
//        return driver.getCurrentUrl().contains("cart.html") && driver.findElement(By.className("cart_list")).isDisplayed();
        return wait.until(ExpectedConditions.urlContains("cart.html")) && driver.findElement(By.className("cart_list")).isDisplayed();
    }

    public boolean isOnShoppingCart(String expectedItemName){
        List<WebElement> items = driver.findElements(cartItems);

        for (WebElement item : items) {
            String name = item.findElement(itemName).getText().trim();
            if (name.equalsIgnoreCase(expectedItemName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItemFromCart(String expectedItemName) {
        String removeId = "remove-" + expectedItemName.toLowerCase().replace(" ", "-");;
        System.out.println(removeId);
        driver.findElement(By.id(removeId)).click();
    }

    public void clickCheckoutButton() {
        try{
            driver.findElement(checkoutButton).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getShoppingCartCount() {
        List<WebElement> items = driver.findElements(cartItems);
        if(items.isEmpty()){
            return 0;
        }
        return items.size();
    }
}
