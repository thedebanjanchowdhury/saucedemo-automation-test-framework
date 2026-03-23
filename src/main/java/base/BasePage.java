package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
   protected WebDriver driver;
   protected WebDriverWait wait;

   public BasePage(WebDriver driver){
       this.driver=driver;
       wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   }

   public void click(By locator) {
       wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
   }

   public void type(By locator, String text) {
       wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
   }

   public boolean isElementDisplayed(By locator) {
       try {
           return driver.findElement(locator).isDisplayed();
       } catch (NoSuchElementException ex) {
           return false;
       }
   }
}
