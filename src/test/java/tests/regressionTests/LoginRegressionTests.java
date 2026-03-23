package tests.regressionTests;

import base.BaseTest;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

@Epic("SauceDemo E2E")
@Feature("Login")
public class LoginRegressionTests extends BaseTest {

    LoginPage loginPage;

    @BeforeClass
    public void pageSetUp() {
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Login flow with locked out user", groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-RO1")
    @Tag("regression")
    public void loginFlowWithLockedOutUser() {
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Sorry, this user has been locked out."));
    }

    @Test(description = "Login with empty username", groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R02")
    @Tag("regression")
    public void loginFlowWithEmptyUsername() {
        loginPage.login("", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorDisplayed(), "Error not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test(description = "Login with empty password", groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-RO3")
    @Tag("regression")
    public void loginFlowWithEmptyPassword() {
        loginPage.login("standard_user", "");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorDisplayed(), "Error not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"));
    }

    @Test(description = "Login with both empty fields", groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R04")
    @Tag("regression")
    public void loginFlowWithBothEmptyFields() {
        loginPage.login("", "");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorDisplayed(), "Error not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test(description = "Login with other user types", groups = {"regression"},
            dataProvider = "loginInputs")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLinks({
        @TmsLink("TC-R07"),
        @TmsLink("TC-R08"),
        @TmsLink("TC-R09"),
        @TmsLink("TC-R10")
    })
    @Tag("regression")
    public void loginFlowWithOtherUserTypes(String username, String password) {
        loginPage.login(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(description = "Login with wrong password", groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R05")
    @Tag("regression")
    public void loginFlowWithWrongPassword() {
        loginPage.login("standard_user", "wrong_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorDisplayed(), "Error not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"));
    }

    @Test(description = "Login with wrong username", groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R06")
    @Tag("regression")
    public void loginFlowWithWrongUsername() {
        loginPage.login("faulty_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorDisplayed(), "Error not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"));
    }

    @Test(description = "Password field masks input", groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R11")
    public void passwordFieldMasksInput() {
        String type = loginPage.getPasswordFieldAttribute();
        Assert.assertEquals(type, "password",
                "Password not masked");
    }

    @Test(description = "Error message close button functionality", groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R12")
    public void errorMessageCloseButtonFunctionality() {
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorBtnDisplayed(), "Error button not displayed");
        loginPage.closeError();
        Assert.assertFalse(loginPage.isErrorBtnDisplayed(), "Error button displayed after closure");
    }

    @DataProvider(name = "loginInputs")
    public Object[][] loginInputs() {
        return new Object[][]{
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"},
        };
    }
}