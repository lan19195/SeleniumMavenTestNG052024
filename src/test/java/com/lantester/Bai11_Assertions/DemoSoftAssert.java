package com.lantester.Bai11_Assertions;

import com.lantester.common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DemoSoftAssert extends BaseTest {
      @Test
      public void testLoginCRM() {
            driver.get("https://crm.anhtester.com/admin/authentication");

            // Khai báo đối tượng SoftAssert
            SoftAssert softAssert = new SoftAssert();


            //Assert caí header với cái text của nó
            String header = driver.findElement(By.xpath("//h1[normalize-space()='Login']")).getText();
            softAssert.assertEquals(header, "Login123", "Giá trị header sai");

            boolean checkButtonLogin = driver.findElement(By.xpath("//button[normalize-space()='Login']")).isEnabled();
            softAssert.assertTrue(checkButtonLogin, "Nút Login không được enable");

            driver.findElement(By.xpath("//input[@id='email']")).sendKeys("admin@example.com");
            driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
            driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
            sleep(2);
            String menuName = driver.findElement(By.xpath("//span[normalize-space()='Customers']")).getText();
            softAssert.assertTrue(menuName.contains("Customer123"), "Giá trị menuName không chứa giá trị mong muốn: " + menuName); // so sánh chứa thông qua java : menuName.contains("Customer")

            softAssert.assertAll();// Tổng hợp lại tất cả các t/h fail

      }

}
