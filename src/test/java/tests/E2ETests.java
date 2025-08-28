package tests;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.InventoryPage;
import pages.ShoppingCartPage;

@Listeners(TestListener.class)
public class E2ETests extends BaseTest {
    @Test
    public void testEndToEndPurchaseFlow() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Step 2: Add product to cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart("Sauce Labs Backpack");

        // Step 3: Go to Shopping Cart
        inventoryPage.goToShoppingCart();

        // Step 4: Checkout
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.clickCheckoutButton();
    }
}
