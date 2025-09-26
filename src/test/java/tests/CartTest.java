package tests;

import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ShoppingCartPage;

@Listeners(TestListener.class)
public class CartTest extends BaseTest{

    @Test
    public void testAddSingleItemToCart() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.isOnShoppingCartPage();
        Assert.assertTrue(shoppingCartPage.isOnShoppingCart("Sauce Labs Backpack"));
    }

    @Test
    public void testRemoveItemFromCart() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.isOnShoppingCartPage();
        shoppingCartPage.removeItemFromCart("Sauce Labs Backpack");
        Assert.assertFalse(shoppingCartPage.isOnShoppingCart("Sauce Labs Backpack"));
    }

    @Test
    public void testGetCartBadgeCount() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bike Light");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(),2,"Badge should be 2.");
    }

    @Test
    public void testResetAppState() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bike Light");
        inventoryPage.resetAppState();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(),0,"Badge should be 0.");
        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.isOnShoppingCartPage();
        Assert.assertEquals(shoppingCartPage.getShoppingCartCount(),0,"Count should be 0.");

    }
}
