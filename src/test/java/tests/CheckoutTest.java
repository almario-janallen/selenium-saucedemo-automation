package tests;

import listeners.TestListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;

@Listeners(TestListener.class)
public class CheckoutTest extends BaseTest{
    @Test
    public void CheckoutMultipleItems() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bike Light");
        inventoryPage.goToShoppingCart();
        driver.findElement(By.className("shopping_cart_link")).click();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.isOnShoppingCartPage());
        shoppingCartPage.clickCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(checkoutInformationPage.isOnCheckoutStepOnePage());

        checkoutInformationPage.setFirstName("FirstName");
        checkoutInformationPage.setLastName("LastName");
        checkoutInformationPage.setPostalCode("12345");
        checkoutInformationPage.clickContinue();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(checkoutOverviewPage.isOnCheckoutStepTwoPage());
        checkoutOverviewPage.finishCheckout();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.isOnCheckoutCompletePage());
        Assert.assertTrue(checkoutCompletePage.isCheckoutComplete(),"Error in Checkout.");
    }
}
