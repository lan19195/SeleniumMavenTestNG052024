package Exercisechapter10.helper;

import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BrowserUtils {

      public static void navigateTo(String url) {
            Logger.log("Navigate to" + url);
            DriverUtils.DRIVER.get().get(url);
      }

      public static void maximize() {
            Logger.log("Maximize browser");
            DriverUtils.DRIVER.get().manage().window().maximize();
      }

      public static void close() {
            Logger.log("Close browser");
            DriverUtils.DRIVER.get().quit();
      }

      public static void waitForTitle(String title, int timeoutInSeconds) {
            WebDriverWait wait = new WebDriverWait(DriverUtils.DRIVER.get(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.titleIs(title));
      }

      public static String getPageURL() {
            String url = DriverUtils.DRIVER.get().getCurrentUrl();
            return url;
      }

      public static void refreshPage() {
            DriverUtils.DRIVER.get().navigate().refresh();
      }

      public static void switchToTab(String newTab) {
            DriverUtils.DRIVER.get().switchTo().newWindow(WindowType.TAB);
            DriverUtils.DRIVER.get().get(newTab);
      }

      public static void switchTabs(String tab) {
            Set<String> windowHandles = DriverUtils.DRIVER.get().getWindowHandles();
            String firstTab = (String) windowHandles.toArray()[0]; //Tab đầu
            String secondTab = (String) windowHandles.toArray()[1]; //Tab thứ hai
            String thirdTab = (String) windowHandles.toArray()[2]; //Tab thứ ba
            if (tab == "firstTab")
                  DriverUtils.DRIVER.get().switchTo().window(firstTab);
            if (tab == "secondTab")
                  DriverUtils.DRIVER.get().switchTo().window(secondTab);
            if (tab == "thirdTab")
                  DriverUtils.DRIVER.get().switchTo().window(thirdTab);
      }

      public static void sleep(double seconds){
            try {
                  Thread.sleep((long) (1000*seconds));
            } catch (InterruptedException e) {
                  throw new RuntimeException(e);
            }
      }

      public static String getTitle(){
            return DriverUtils.DRIVER.get().getTitle();
      }

      public static void handleAlert(String option){
            if(option == "cancel")
                  DriverUtils.DRIVER.get().switchTo().alert().dismiss();
            if(option == "ok")
                  DriverUtils.DRIVER.get().switchTo().alert().accept();
      }

}
