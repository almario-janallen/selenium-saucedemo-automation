package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
public class CheckoutOverviewPage {
    private final WebDriver driver;

    //static locators
    private final By header = By.className("title");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    private By productName(String productName) {
        return By.xpath("//div[@class='inventory_item_name' and text() =' "+productName+"']");
    }

    private By productPrice(String itemName) {
        return By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']");
    }

    public boolean isOnCheckoutStepTwoPage() {
        return driver.findElement(finishButton).isDisplayed() && getHeaderText().equals("Checkout: Overview");
    }

    public void finishCheckout() {
        driver.findElement(finishButton).click();
    }
}
