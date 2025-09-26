package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
public class CheckoutOverviewPage {
    private final WebDriver driver;

    //static locators
    private final By header = By.className("title");
    private final By finishButton = By.id("finish");
    private final By subtotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    private By productNameLocator(String productName) {
        return By.xpath("//div[@class='inventory_item_name' and text() ='"+productName+"']");
    }

    private By productPriceLocator(String productName) {
        return By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']");
    }

    public boolean isOnCheckoutOverviewPage() {
        return driver.findElement(finishButton).isDisplayed() && getHeaderText().equals("Checkout: Overview");
    }

    public double getItemPrice(String itemName) {
        String itemPrice = driver.findElement(productPriceLocator(itemName)).getText();
        return Double.parseDouble(itemPrice.replace("$","").trim());
    }

    public boolean isItemInCheckoutOverviewPage(String itemName){
        return driver.findElement(productNameLocator(itemName)).isDisplayed();
    }

    public double getItemSubTotal() {
        String subtotalText = driver.findElement(subtotal).getText();
        return Double.parseDouble(subtotalText.replace("Item total: $", "").trim());
    }

    public double getTax() {
        String taxText = driver.findElement(tax).getText();
        return Double.parseDouble(taxText.replace("Tax: $", "").trim());
    }

    public double getItemTotal() {
        String totalText = driver.findElement(total).getText();
        return Double.parseDouble(totalText.replace("Total: $", "").trim());
    }

    public void finishCheckout() {
        driver.findElement(finishButton).click();
    }
}
