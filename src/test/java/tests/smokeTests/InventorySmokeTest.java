package tests.smokeTests;

import base.BaseTest;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Epic("SauceDemo E2E")
@Feature("Inventory")
public class InventorySmokeTest extends BaseTest {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeClass
    public void beforeClass(){
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Inventory page loads with product cards")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-S03")
    @Tag("smoke")
    public void inventoryPageLoadsProductCards(){
        Assert.assertTrue(inventoryPage.isPageLoaded(),
                "Inventory page not loaded");
    }

}
