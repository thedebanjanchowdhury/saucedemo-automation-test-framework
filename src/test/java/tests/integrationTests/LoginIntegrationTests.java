package tests.integrationTests;

import base.BaseTest;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

@Epic("SauceDemo E2E")
@Feature("Login")
public class LoginIntegrationTests extends BaseTest {

    LoginPage loginPage;

    @BeforeClass
    public void pageSetUp() {
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void resetApp() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(description = "Restrict browser back after login")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-I01")
    @Tag("integration")
    public void restrictBrowserBackAfterLogin() {
        loginPage.login("standard_user", "secret_sauce");
        driver.navigate().back();
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/",
                "Redirected to login page after login");
    }

    @Test(description = "Direct inventory page access")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-I02")
    @Tag("integration")
    public void directInventoryPageAccess() {
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/",
                "Direct inventory page accessed without login");
    }
}