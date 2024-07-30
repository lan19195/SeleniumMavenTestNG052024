package Exercisechapter10.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtils {
      public static WebElement findElement(By locator) {
            return DriverUtils.DRIVER.get().findElement(locator);
      }

      public static List<WebElement> findElements(By locator) {
            return DriverUtils.DRIVER.get().findElements(locator);
      }

      public static void waitForElementExists(By locator, int timeoutInSeconds) {
            WebDriverWait wait = new WebDriverWait(DriverUtils.DRIVER.get(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
      }

      public static void waitForElementClickable(By locator, int timeoutInSeconds) {
            WebDriverWait wait = new WebDriverWait(DriverUtils.DRIVER.get(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
      }

      public static boolean isElementExits(By locator, int timeoutInSeconds) {
            try {
                  waitForElementExists(locator, timeoutInSeconds);
                  return true;
            } catch (Exception e) {
                  return false;
            }
      }

      public static void scrollIntoView(WebElement element){
            var script = "arguments[0].scrollIntoView(true);";
            ((JavascriptExecutor) DriverUtils.DRIVER.get()).executeScript(script,element);
      }

      public static void clickElement(By locator){
            WebElement element = DriverUtils.DRIVER.get().findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor)DriverUtils.DRIVER.get();
            executor.executeScript("arguments[0].click();", element);
      }

      public static void selectDropDown(By dropDownName, String visibleText ){
            Select select = new Select(DriverUtils.DRIVER.get().findElement(dropDownName));
            select.selectByVisibleText(visibleText);
      }

      public static String selectedOption(By dropDownName){
            Select select = new Select(DriverUtils.DRIVER.get().findElement(dropDownName));
            WebElement option = select.getFirstSelectedOption();
            String defaultItem = option.getText();
            return defaultItem;
      }

}

