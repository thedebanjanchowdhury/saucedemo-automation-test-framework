package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    private static final By INVENTORY_CONTAINER     = By.id("inventory_container");
    private static final By PRODUCT_CARD            = By.className("inventory_item");
    private static final By PRODUCT_IMAGE           = By.className("inventory_item_img");
    private static final By PRODUCT_NAME            = By.xpath("//div[@class='inventory_item_label']/a");
    private static final By PRODUCT_DESC            = By.className("inventory_item_desc");
    private static final By PRODUCT_PRICE           = By.className("inventory_item_price");
    private static final By PRODUCT_ATC_BTN         = By.xpath("//button[contains(text(),'Add to cart')]");
    private static final By SORT_DROPDOWN           = By.className("product_sort_container");
    private static final By PAGE_CART_BTN           = By.id("shopping_cart_container");
    private static final By CART_BADGE              = By.className("shopping_cart_badge");



    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(INVENTORY_CONTAINER)).isDisplayed()
                && !driver.findElements(PRODUCT_CARD).isEmpty();
    }

    public List<WebElement> getAllProductCards() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_CARD));
        return driver.findElements(PRODUCT_CARD);
    }

    public boolean hasImage(WebElement card) {
        WebElement img = card.findElement(PRODUCT_IMAGE);
        return img.isDisplayed();
    }

    public boolean hasName(WebElement card) {
        WebElement name = card.findElement(PRODUCT_NAME);
        return name.isDisplayed() && !name.getText().isEmpty();
    }

    public boolean hasDescription(WebElement card) {
        WebElement desc = card.findElement(PRODUCT_DESC);
        return desc.isDisplayed() &&  !desc.getText().isEmpty();
    }

    public boolean hasPrice(WebElement card) {
        WebElement price = card.findElement(PRODUCT_PRICE);
        return price.isDisplayed() &&  !price.getText().isEmpty();
    }

    public boolean hasAddToCartButton(WebElement card) {
        WebElement cart = card.findElement(PRODUCT_ATC_BTN);
        return cart.isDisplayed();
    }

    public boolean hasSortButton() {
        WebElement sortBtn = driver.findElement(SORT_DROPDOWN);
        return sortBtn.isDisplayed() && !sortBtn.getText().isEmpty();
    }

    public boolean hasCartButton() {
        WebElement cartBtn = driver.findElement(PAGE_CART_BTN);
        return cartBtn.isDisplayed();
    }

    public List<String> getProductNames() {
        return driver.findElements(PRODUCT_NAME)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void sortProducts(String sortBy) {
        Select select = new Select(driver.findElement(SORT_DROPDOWN));
        select.selectByValue(sortBy);
    }

    public List<Double> getProductPrices() {
        return driver.findElements(PRODUCT_PRICE)
            .stream()
            .map(WebElement::getText)
            .map(s -> s.replace("$", "").trim())
            .map(Double::parseDouble)
            .collect(Collectors.toList());
    }

    public void addToCart(WebElement product) {
        product.findElement(PRODUCT_ATC_BTN).click();
    }

    public String getBtnText(WebElement card) {
        return card.findElement(PRODUCT_ATC_BTN).getText();
    }

    public String getBadgeCount() {
        return wait.until(ExpectedConditions.
                visibilityOfElementLocated(CART_BADGE)).getText();
    }

    public void gotoProductPage(WebElement product){
        product.findElement(PRODUCT_NAME).click();
    }
}
