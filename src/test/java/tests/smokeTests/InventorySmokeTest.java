package tests.smokeTests;

import base.BaseTest;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import java.util.List;

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

    @Test(description = "Inventory page loads with product cards", groups = {"smoke"},
    priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-S03")
    @Tag("smoke")
    public void inventoryPageLoadsProductCards(){
        Assert.assertTrue(inventoryPage.isPageLoaded(),
                "Inventory page not loaded");
    }

    @Test(description = "Product cards display relevant information", groups = {"smoke"},
    priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-S04")
    @Tag("smoke")
    public void productCardDisplaysRelevantInformation(){
        List<WebElement> cards = inventoryPage.getAllProductCards();
        Assert.assertFalse(cards.isEmpty(), "No product cards found in inventory page");

        for (int i = 0; i < cards.size(); i++){
            WebElement card = cards.get(i);
            String cardLabel = "Card[" + i + "]";

            Assert.assertTrue(inventoryPage.hasImage(card),
                    cardLabel + "product image missing");
            Assert.assertTrue(inventoryPage.hasName(card),
                    cardLabel + "product name missing");
            Assert.assertTrue(inventoryPage.hasPrice(card),
                    cardLabel + "product price missing");
            Assert.assertTrue(inventoryPage.hasDescription(card),
                    cardLabel + "product description missing");
            Assert.assertTrue(inventoryPage.hasAddToCartButton(card),
                    cardLabel + "add to cart button missing");
        }
    }

    @Test(description = "Login redirects to inventory page", groups = {"smoke"}, priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-S05")
    @Tag("smoke")
    public void loginRedirectsToInventoryPage(){
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
                "Login failed");
    }

    @Test(description = "Sort dropdown present", groups = {"smoke"}, priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-S06")
    @Tag("smoke")
    public void sortDropdownPresent() {
        Assert.assertTrue(inventoryPage.hasSortButton(), "Sort button not found");
    }

    @Test(description = "Cart button present", groups = {"smoke"}, priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-S07")
    @Tag("smoke")
    public void cartButtonPresent() {
        Assert.assertTrue(inventoryPage.hasCartButton(), "Cart button not found");
    }

}
