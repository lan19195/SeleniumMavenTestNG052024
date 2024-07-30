package Exercisechapter10.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtils {

      protected static ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
      public static void initDriver(String browser) throws Exception {
            Logger.log("Creat"+browser);
            switch(browser) {
                  case "chrome":
                        DRIVER.set(new ChromeDriver());
                        break;
                  case "firefox":
                        DRIVER.set(new FirefoxDriver());
                        break;
                  default:
                        var message = "Browser" + browser+"was not supported";
                        Logger.log(message);
                        throw new Exception(message);
            }
      }
}
