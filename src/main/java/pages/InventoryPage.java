package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {

    private static final By INVENTORY_CONTAINER = By.id("inventory_container");
    private static final By PRODUCT_CARD = By.className("inventory_item");
    private static final By PRODUCT_IMAGE = By.className("inventory_item_img");
    private static final By PRODUCT_NAME = By.xpath("//div[@class='inventory_item_label']/following-sibling::a");



    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(INVENTORY_CONTAINER)).isDisplayed()
                && !driver.findElements(PRODUCT_CARD).isEmpty();
    }



}
