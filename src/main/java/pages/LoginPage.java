package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.className("error-message-container");
    private final By errorCloseBtn = By.cssSelector("[data-test='error-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        click(loginButton);
    }

    public boolean errorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginButton)
                && isElementDisplayed(usernameInput)
                && isElementDisplayed(passwordInput);
    }

    public String getErrorMessage() {
       return driver.findElement(errorMessage).getText();
    }

    public String getPasswordFieldAttribute() {
        return driver.findElement(passwordInput).getAttribute("type");
    }

    public boolean isErrorBtnDisplayed() {
        return isElementDisplayed(errorCloseBtn);
    }

    public void closeError() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        wait.until(ExpectedConditions.elementToBeClickable(errorCloseBtn));
        driver.findElement(errorCloseBtn).click();
    }
}
