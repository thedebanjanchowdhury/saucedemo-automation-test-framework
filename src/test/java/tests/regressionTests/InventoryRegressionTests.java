package tests.regressionTests;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import io.qameta.allure.testng.Tag;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Epic("SauceDemo E2E")
@Feature("Inventory")
public class InventoryRegressionTests extends BaseTest {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeClass
    public void beforeClass(){
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Sort by 'Name (A to Z)' display products in ascending alphabetical order",
    groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R13")
    @Tag("regression")
    public void verifyAscendingNameSort() {
        List<String> expected  = inventoryPage.getProductNames();
        Collections.sort(expected);

        inventoryPage.sortProducts("az");
        List<String> actual = inventoryPage.getProductNames();

        Assert.assertEquals(actual, expected,
                "Ascending sort on product name not working");
    }

    @Test(description = "")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R13")
    @Tag("regression")

}
