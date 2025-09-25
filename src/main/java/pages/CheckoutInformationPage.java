package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class CheckoutInformationPage {
    private final WebDriver driver;

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By header = By.className("title");

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    // locators
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");


    public void fillCheckoutInformation(String firstName, String lastName, String postalCode){
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }


    public  void finishCheckout() {
        driver.findElement(continueButton).click();
    }

    public boolean isOnCheckoutInformationPage() {
        return driver.findElement(header).isDisplayed() && getHeaderText().equals("Checkout: Your Information");
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText().trim();
    }
}
