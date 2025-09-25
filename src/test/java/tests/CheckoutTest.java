package tests;

import listeners.TestListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;

@Listeners(TestListener.class)
public class CheckoutTest extends BaseTest{

    @BeforeMethod
    public void loginAndAddItems(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bike Light");
        inventoryPage.goToShoppingCart();
        driver.findElement(By.className("shopping_cart_link")).click();
    }
    @Test
    public void CheckoutMultipleItems() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.isOnShoppingCartPage());
        shoppingCartPage.clickCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(checkoutInformationPage.isOnCheckoutInformationPage());

        checkoutInformationPage.fillCheckoutInformation("FirstName","LastName","12345");
        checkoutInformationPage.finishCheckout();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(checkoutOverviewPage.isOnCheckoutOverviewPage());
        checkoutOverviewPage.finishCheckout();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.isOnCheckoutCompletePage());
        Assert.assertTrue(checkoutCompletePage.isCheckoutComplete(),"Error in Checkout.");
    }

    @Test
    public void testCheckoutMissingFirstName(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.isOnShoppingCartPage());
        shoppingCartPage.clickCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(checkoutInformationPage.isOnCheckoutInformationPage());

        checkoutInformationPage.fillCheckoutInformation("","LastName","12345");
        checkoutInformationPage.finishCheckout();

        Assert.assertEquals(checkoutInformationPage.getErrorMessage(),"Error: First Name is required");
    }

    @Test
    public void testCheckoutMissingLastName(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.isOnShoppingCartPage());
        shoppingCartPage.clickCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(checkoutInformationPage.isOnCheckoutInformationPage());

        checkoutInformationPage.fillCheckoutInformation("FirstName","","12345");
        checkoutInformationPage.finishCheckout();

        Assert.assertEquals(checkoutInformationPage.getErrorMessage(),"Error: Last Name is required");
    }

    @Test
    public void testCheckoutMissingPostalCode(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.isOnShoppingCartPage());
        shoppingCartPage.clickCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(checkoutInformationPage.isOnCheckoutInformationPage());

        checkoutInformationPage.fillCheckoutInformation("FirstName","LastName","");
        checkoutInformationPage.finishCheckout();

        Assert.assertEquals(checkoutInformationPage.getErrorMessage(),"Error: Postal Code is required");
    }

    @Test
    public void testCheckoutTotalPrice() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.isOnShoppingCartPage());
        shoppingCartPage.clickCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(checkoutInformationPage.isOnCheckoutInformationPage());

        checkoutInformationPage.fillCheckoutInformation("FirstName","LastName","12345");
        checkoutInformationPage.finishCheckout();

        CheckoutOverviewPage checkout = new CheckoutOverviewPage(driver);
        Assert.assertTrue(checkout.isOnCheckoutOverviewPage());

        checkout.isItemInCheckoutOverviewPage("Sauce Labs Backpack");
        checkout.isItemInCheckoutOverviewPage("Sauce Labs Bike Light");

        double totalPrice = checkout.getItemPrice("Sauce Labs Backpack") + checkout.getItemPrice("Sauce Labs Bike Light");
        Assert.assertEquals(checkout.getItemTotal(),totalPrice,0.01,"computed total is " + totalPrice + " and expected total is " + checkout.getItemTotal());
        checkout.finishCheckout();
    }
}
