package tests;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ShoppingCartPage;

@Listeners(TestListener.class)
public class CartTest extends BaseTest{

    @BeforeMethod
    public void loginAndGoToProducts() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");
    }

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
}
