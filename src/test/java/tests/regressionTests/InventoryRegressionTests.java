package tests.regressionTests;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
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



}
