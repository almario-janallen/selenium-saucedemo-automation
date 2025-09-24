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

    public void setFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void setPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public  void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public boolean isOnCheckoutStepOnePage() {
        return driver.findElement(header).isDisplayed() && getHeaderText().equals("Checkout: Your Information");
    }
}
