package tests.smokeTests;

import base.BaseTest;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

@Epic("SauceDemo E2E")
@Feature("Login")
public class LoginSmokeTest extends BaseTest {
    LoginPage loginPage;

    @BeforeClass
    public void pageSetUp() {
       loginPage = new LoginPage(driver);
    }

    @Test(description = "Valid login flow", groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-S01")
    @Tag("smoke")
    public void validLoginFlow() {
       loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Malfunction in login redirection");
    }

    @Test(description = "Login screen properly displayed", groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-S02")
    @Tag("smoke")
    public void loginScreenProperlyDisplayed() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Malfunction in login page display");
    }
}
