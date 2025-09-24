package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private final WebDriver driver;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    // static locator
    private final By header = By.className("title");
    private final By completeHeader = By.className("complete-header");

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    public String getCompleteHeaderText() {
        return driver.findElement(completeHeader).getText();
    }

    public boolean isOnCheckoutCompletePage() {
        return driver.findElement(header).isDisplayed() && getHeaderText().equals("Checkout: Complete!");
    }

    public boolean isCheckoutComplete() {
        return getCompleteHeaderText().equals("Thank you for your order!");
    }


}
