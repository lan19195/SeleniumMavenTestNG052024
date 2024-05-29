package com.lantester.Bai11_Assertions;

import com.lantester.common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoHardAssert extends BaseTest {
      @Test
      public void testLoginCRM(){
            driver.get("https://crm.anhtester.com/admin/authentication");

            //Assert caí header với cái text của nó
            String header = driver.findElement(By.xpath("//h1[normalize-space()='Login']")).getText();
            Assert.assertEquals(header,"Login", "Giá trị header sai");

            boolean checkButtonLogin = driver.findElement(By.xpath("//button[normalize-space()='Login']")).isEnabled();
            Assert.assertTrue(checkButtonLogin,"Nút Login không được enable");

            driver.findElement(By.xpath("//input[@id='email']")).sendKeys("admin@example.com");
            driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
            driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
            sleep(2);
            String menuName = driver.findElement(By.xpath("//span[normalize-space()='Customers']")).getText();
            Assert.assertTrue(menuName.contains("Customer123"), "Giá trị menuName không chứa giá trị mong muốn: "+ menuName); // so sánh chứa thông qua java : menuName.contains("Customer")


      }

}
