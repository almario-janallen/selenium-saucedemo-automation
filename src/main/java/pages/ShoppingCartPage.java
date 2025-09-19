package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private By cartItems = By.className("cart_item");
    private By itemName = By.className("inventory_item_name");
    private By removeButton = By.tagName("btn btn_secondary btn_small cart_button");

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
