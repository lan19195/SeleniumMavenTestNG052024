package Exercisechapter10.testcases;

import Exercisechapter10.helper.Constant;
import Exercisechapter10.helper.BrowserUtils;
import Exercisechapter10.helper.DriverUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBase {
      @Parameters({"browser"})
      @BeforeMethod
      public void setup(String browser) throws Exception {
            DriverUtils.initDriver(browser);
            BrowserUtils.navigateTo(Constant.URL);
            BrowserUtils.maximize();

      }

      @AfterMethod
      public void teardown(){
            BrowserUtils.close();
      }
}
