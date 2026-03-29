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

import org.openqa.selenium.WebElement;
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

    @Test(description = "Sort by 'Name (Z to A)' display product in descending order",
    groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R14")
    @Tag("regression")
    public void verifyDescendingNameSort() {
        List<String> expected = inventoryPage.getProductNames();
        Collections.reverse(expected);

        inventoryPage.sortProducts("za");
        List<String>actual = inventoryPage.getProductNames();
        Assert.assertEquals(actual, expected,
                "Descending sort on product name not working");
    }

    @Test(description = "Sort by 'Price (low to high)' display product in ascending order",
    groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R15")
    @Tag("regression")
    public void verifyAscendingPriceSort() {
        List<Double> expected = inventoryPage.getProductPrices();
        Collections.sort(expected);

        inventoryPage.sortProducts("lohi");
        List<Double> actual = inventoryPage.getProductPrices();
        Assert.assertEquals(actual, expected,
                "Product price ascending sort not worked");
    }    

    @Test(description = "Add to cart increments cart bagde by 1",
    groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R16")
    @Tag("regression")
    public void verifyCartBadge() {
        WebElement product = inventoryPage.getAllProductCards().get(0);
        inventoryPage.addToCart(product);

        String badgeCount = inventoryPage.getBadgeCount();
        Assert.assertEquals(badgeCount, "1",
                "Mismatch in cart badge product count, expected 1 but found" + badgeCount);
    }

    @Test(description = "Multiple product carted returns correct badge count",
    groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R17")
    @Tag("regression")
    public void verifyMultipleProductCartBadge() {
        WebElement product1 = inventoryPage.getAllProductCards().get(0);
        WebElement product2 = inventoryPage.getAllProductCards().get(1);
        WebElement product3 = inventoryPage.getAllProductCards().get(2);

        inventoryPage.addToCart(product1);
        inventoryPage.addToCart(product2);
        inventoryPage.addToCart(product3);

        String badgeCount = inventoryPage.getBadgeCount();
        Assert.assertEquals("badgeCount", 3,
                "Mismatch in badge product count, expected 3 but found" + badgeCount);
    }

    @Test(description = "Verify add to cart button state change",
    groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R18")
    @Tag("regression")
    public void verifyCartButtonStateChange() {
        WebElement product = inventoryPage.getAllProductCards().get(0);
        inventoryPage.addToCart(product);

        String btnText = inventoryPage.getBtnText(product);
        Assert.assertEquals(btnText, "Remove",
                "Button state after cart addition not changed");
    }

    @Test(description = "Remove button click converts button state to 'Add to cart'",
    groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R19")
    @Tag("regression")
    public void verifyCartBtnStateChange2() {
        WebElement product = inventoryPage.getAllProductCards().get(0);
        inventoryPage.addToCart(product);
        inventoryPage.addToCart(product);

        String btnText = inventoryPage.getBtnText(product);
        Assert.assertEquals(btnText, "Add to cart",
                "Malfunction state after cart remove not changed");
    }

    @Test(description = "Product name redirects to specific product page",
    groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-R20")
    @Tag("regression")
    public void verifyProductNameCorrectRedirection() {
        WebElement product = inventoryPage.getAllProductCards().get(0);
        inventoryPage.gotoProductPage(product);

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html?id="),
                "Clicked on product name, malfunction in redirection");
    }
}
