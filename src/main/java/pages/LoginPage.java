package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    //Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    //Constructors
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/") && driver.findElement(By.className("login_container")).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
    }
}
