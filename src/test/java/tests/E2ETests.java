package tests;

import listeners.TestListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import pages.*;

@Listeners(TestListener.class)
public class E2ETests extends BaseTest {
    @Test
    public void testEndToEndPurchaseFlow() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isOnLoginPage());
        loginPage.login("standard_user", "secret_sauce");

        // Step 2: Add product to cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isOnInventoryPage());
//        inventoryPage.handleLoginAlert();
        inventoryPage.addToCart("Sauce Labs Backpack");

        /*
        Issue: Cannot click shopping cart
         */
        // Step 3: Go to Shopping Cart
        inventoryPage.goToShoppingCart();
        driver.findElement(By.className("shopping_cart_link")).click();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.isOnShoppingCartPage());

        // Step 4: Checkout
        shoppingCartPage.clickCheckoutButton();

        // Step 5: Enter User Information
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
        Assert.assertTrue(checkoutStepOnePage.isOnCheckoutStepOnePage());

        checkoutStepOnePage.setFirstName("FirstName");
        checkoutStepOnePage.setLastName("LastName");
        checkoutStepOnePage.setPostalCode("12345");
        checkoutStepOnePage.clickContinue();

        // Step 6: Finish Checkout
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        Assert.assertTrue(checkoutStepTwoPage.isOnCheckoutStepTwoPage());
        checkoutStepTwoPage.finishCheckout();

        // Step 7: Validate if Checkout is complete
        CheckoutComplete checkoutComplete = new CheckoutComplete(driver);
        Assert.assertTrue(checkoutComplete.isOnCheckoutCompletePage());
        Assert.assertTrue(checkoutComplete.isCheckoutComplete(),"Error in Checkout.");
    }
}
