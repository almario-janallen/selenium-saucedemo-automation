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
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isOnInventoryPage());
        inventoryPage.addToCart("Sauce Labs Backpack");

        /*
        Issue: Cannot click shopping cart
         */
        // Step 3: Go to Shopping Cart
        driver.findElement(By.className("shopping_cart_link")).click();
        inventoryPage.goToShoppingCart();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.isOnShoppingCartPage());

        // Step 4: Checkout
        shoppingCartPage.clickCheckoutButton();

        // Step 5: Enter User Information
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(checkoutInformationPage.isOnCheckoutInformationPage());

        checkoutInformationPage.fillCheckoutInformation("FirstName","LastName","12345");
        checkoutInformationPage.finishCheckout();

        // Step 6: Finish Checkout
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(checkoutOverviewPage.isOnCheckoutOverviewPage());
        checkoutOverviewPage.finishCheckout();

        // Step 7: Validate if Checkout is complete
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.isOnCheckoutCompletePage());
        Assert.assertTrue(checkoutCompletePage.isCheckoutComplete(),"Error in Checkout.");
    }
}
