package com.lantester.Bai16_ThucHanhCRM;

import com.lantester.common.BaseTest;
import com.lantester.locators.locatorCRM;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.lantester.keywords.ActionKeywords.clickElement;
import static com.lantester.keywords.ActionKeywords.setText;

public class LoginTest extends BaseTest {

      @Test
      public void testLoginSuccess(){
            driver.get(InfoCRM.URL);
            waitForPageLoaded();
            setText(locatorCRM.inputEmail,InfoCRM.EMAIL);
            setText(locatorCRM.inputPassword,InfoCRM.PASSWORD);
            clickElement(locatorCRM.buttonLogin);
            waitForPageLoaded();
            boolean checkURLNotAuthen = driver.getCurrentUrl().contains("authentication");
            Assert.assertFalse(checkURLNotAuthen);
//            boolean checkURLContainsDashboard = driver.getCurrentUrl().contains("admin");
//            Assert.assertTrue(checkURLContainsDashboard);
            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.menuDashboard)).isDisplayed(),"không đến được trang Dashboard");
      }

      @Test
      public void testLoginWithEmailInvalid(){
            driver.get(InfoCRM.URL);
            waitForPageLoaded();
            setText(locatorCRM.inputEmail,"admin123@example.com");
            setText(locatorCRM.inputPassword,InfoCRM.PASSWORD);
            clickElement(locatorCRM.buttonLogin);
            waitForPageLoaded();
            boolean checkURLNotAuthen = driver.getCurrentUrl().contains("authentication");
            Assert.assertTrue(checkURLNotAuthen);
//            boolean checkURLContainsDashboard = driver.getCurrentUrl().contains("admin");
//            Assert.assertFalse(checkURLContainsDashboard);
            //Assert.assertTrue(driver.findElements(By.xpath(locatorCRM.menuDashboard)).size()>0,"không đến được trang Dashboard");
            //hoặc
            Assert.assertFalse(checkElementExist(locatorCRM.menuDashboard),"Fail, đến được trang Dashboard");
            Assert.assertTrue(checkElementExist(locatorCRM.alertMessage),"Fail, Alert message is displayed");
            Assert.assertEquals(getText(locatorCRM.alertMessage),"Invalid email or password");

      }

      @Test
      public void testLoginWithPasswordInvalid(){
            driver.get(InfoCRM.URL);
            waitForPageLoaded();
            setText(locatorCRM.inputEmail, InfoCRM.EMAIL);
            setText(locatorCRM.inputPassword,"123");
            clickElement(locatorCRM.buttonLogin);
            waitForPageLoaded();
            boolean checkURLNotAuthen = driver.getCurrentUrl().contains("authentication");
            Assert.assertTrue(checkURLNotAuthen);
//            boolean checkURLContainsDashboard = driver.getCurrentUrl().contains("admin");
//            Assert.assertFalse(checkURLContainsDashboard);
            //Assert.assertTrue(driver.findElements(By.xpath(locatorCRM.menuDashboard)).size()>0,"không đến được trang Dashboard");
            //hoặc
            Assert.assertFalse(checkElementExist(locatorCRM.menuDashboard),"Fail, đến được trang Dashboard");
            Assert.assertTrue(checkElementExist(locatorCRM.alertMessage),"Fail, Alert message is displayed");
            Assert.assertEquals(getText(locatorCRM.alertMessage),"Invalid email or password");
      }
}
