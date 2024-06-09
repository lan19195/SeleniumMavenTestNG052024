package com.lantester.thuchanh;

import com.lantester.common.BaseTest;
import com.lantester.keywords.ActionKeywords;
import com.lantester.locators.locatorCRM;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewCustomer extends BaseTest {
      String COMPANY_NAME = "Selenium Java 05/2024 3";
      @Test
      public void loginCRM(){
            //Khởi tạo đối tượng class cho ActionKeywords để nhận giá trị driver
            new ActionKeywords(driver);
            driver.get("https://crm.anhtester.com/admin/authentication");
            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.headerLoginPage)).isDisplayed(),"Header không tồn tại,không phải trang Login");
//            driver.findElement(By.xpath(locatorCRM.inputEmail)).sendKeys("admin@example.com");
//            driver.findElement(By.xpath(locatorCRM.inputPassword)).sendKeys("123456");
//            driver.findElement(By.xpath(locatorCRM.buttonLogin)).click();
            ActionKeywords.setText(locatorCRM.inputEmail,"admin@example.com");
            ActionKeywords.setText(locatorCRM.inputPassword,"123456");
            ActionKeywords.clickElement(locatorCRM.buttonLogin);
            sleep(1);
            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.menuDashboard)).isDisplayed(),"không đến được trang Dashboard");
      }


      @Test
      public void testAddNewCustomer() {
            loginCRM();

//            //Khởi tạo đối tượng class cho ActionKeywords để nhận giá trị driver
//            new ActionKeywords(driver);
//            driver.get("https://crm.anhtester.com/admin/authentication");
//            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.headerLoginPage)).isDisplayed(),"Header không tồn tại,không phải trang Login");
////            driver.findElement(By.xpath(locatorCRM.inputEmail)).sendKeys("admin@example.com");
////            driver.findElement(By.xpath(locatorCRM.inputPassword)).sendKeys("123456");
////            driver.findElement(By.xpath(locatorCRM.buttonLogin)).click();
//            ActionKeywords.setText(locatorCRM.inputEmail,"admin@example.com");
//            ActionKeywords.setText(locatorCRM.inputPassword,"123456");
//            ActionKeywords.clickElement(locatorCRM.buttonLogin);



            driver.findElement(By.xpath(locatorCRM.menuCustomers)).click();
            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.headerCustomersPage)).isDisplayed(),"không đến được trang Customer");
            Assert.assertEquals(driver.findElement(By.xpath(locatorCRM.headerCustomersPage)).getText(),"Customers Summary", "tên header Customer page không đúng");
            sleep(1);
            driver.findElement(By.xpath(locatorCRM.buttonAddNewCustomer)).click();

            // Add new Customer
            driver.findElement(By.xpath(locatorCRM.inputCompanyName)).sendKeys(COMPANY_NAME);
            driver.findElement(By.xpath(locatorCRM.inputVatNumber)).sendKeys("10");
            driver.findElement(By.xpath(locatorCRM.inputPhone)).sendKeys("123456");
            driver.findElement(By.xpath(locatorCRM.inputWebsite)).sendKeys("https://anhtester.com/");
            driver.findElement(By.xpath(locatorCRM.dropdownGroups)).click();
            sleep(1);
            driver.findElement(By.xpath(locatorCRM.inputSearchGroup)).sendKeys("VIP");
            sleep(1);
            driver.findElement(By.xpath(locatorCRM.inputSearchGroup)).sendKeys(Keys.ENTER);
            driver.findElement(By.xpath(locatorCRM.dropdownGroups)).click();
            driver.findElement(By.xpath(locatorCRM.inputAddress)).sendKeys("Ha Noi");
            driver.findElement(By.xpath(locatorCRM.inputCity)).sendKeys("Ha Noi");
            driver.findElement(By.xpath(locatorCRM.inputState)).sendKeys("Ha Noi");
            driver.findElement(By.xpath(locatorCRM.inputZipCode)).sendKeys("10000");
            driver.findElement(By.xpath(locatorCRM.buttonCountry)).click();
            sleep(1);
            driver.findElement(By.xpath(locatorCRM.inputSearchCountry)).sendKeys("Vietnam");
            sleep(1);
            driver.findElement(By.xpath(locatorCRM.inputSearchCountry)).sendKeys(Keys.ENTER);
            driver.findElement(By.xpath(locatorCRM.buttonSaveCustomer)).click();
            sleep(3);

            //Search customer vừa tạo
            driver.findElement(By.xpath(locatorCRM.menuCustomers)).click();
            driver.findElement(By.xpath(locatorCRM.inputSearchCustomers)).sendKeys(COMPANY_NAME);
            sleep(2);
            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.firstItemCustomerOnTable)).isDisplayed(),"Không tìm thấy Customer");

            // kiểm tra giá trị sau khi addNew
            driver.findElement(By.xpath(locatorCRM.firstItemCustomerOnTable)).click();
            Assert.assertEquals(driver.findElement(By.xpath(locatorCRM.inputCompanyName)).getAttribute("value"),COMPANY_NAME,"tên Company không đúng");
            Assert.assertEquals(driver.findElement(By.xpath(locatorCRM.inputVatNumber)).getAttribute("value"),"10","VAT không đúng");
            Assert.assertEquals(driver.findElement(By.xpath(locatorCRM.inputPhone)).getAttribute("value"),"123456","Phone không đúng");
            Assert.assertEquals(driver.findElement(By.xpath(locatorCRM.inputWebsite)).getAttribute("value"),"https://anhtester.com/","website không đúng");
      }

      @Test
      public void testAddNewContactForCustomer(){
            loginCRM();
            driver.findElement(By.xpath(locatorCRM.menuCustomers)).click();
            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.headerCustomersPage)).isDisplayed(),"không đến được trang Customer");
            Assert.assertEquals(driver.findElement(By.xpath(locatorCRM.headerCustomersPage)).getText(),"Customers Summary", "tên header Customer page không đúng");
            sleep(1);
            //Search customer vừa tạo
            driver.findElement(By.xpath(locatorCRM.menuCustomers)).click();
            driver.findElement(By.xpath(locatorCRM.inputSearchCustomers)).sendKeys(COMPANY_NAME);
            sleep(2);
            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.firstItemCustomerOnTable)).isDisplayed(),"Không tìm thấy Customer");
            driver.findElement(By.xpath(locatorCRM.firstItemCustomerOnTable)).click();
            driver.findElement(By.xpath(locatorCRM.menuContact)).click();
            sleep(1);
            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.headerContactPage)).isDisplayed(),"Không tìm thấy trang Contact");
            driver.findElement(By.xpath(locatorCRM.buttonAddNewContact)).click();
            sleep(1);
            Assert.assertTrue(driver.findElement(By.xpath(locatorCRM.headerAddNewContactDialog)).isDisplayed(),"Không tìm thấy trang dialog New Contact");
            sleep(1);
            driver.findElement(By.xpath(locatorCRM.inputProfileImage)).sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\datatest\\z5125117839687_669f9dd1e9f1da694c7e9f43ee91daa5.jpg");
            sleep(2);




      }
}
