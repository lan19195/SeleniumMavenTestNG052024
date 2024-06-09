package com.lantester.Bai14_JavascriptExecutor;

import com.lantester.common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DemoJavascriptExecutor extends BaseTest {

      @Test
      public void testJavascriptExecutor01() {
            driver.get("https://cms.anhtester.com/");
            sleep(1);
            // viết theo dạng By, String hoặc WebElement đều được
            //By buttonCloseWelcomeDialog = By.xpath("//button[@data-key='website-popup']");
            WebElement buttonCloseWelcomeDialog = driver.findElement(By.xpath("//button[@data-key='website-popup']"));
            WebElement menuBlogs = driver.findElement(By.xpath("//a[normalize-space()='Blogs']"));
            //a[normalize-space()='View All Categories']
            WebElement buttonViewAllCategories = driver.findElement(By.xpath("//a[normalize-space()='View All Categories']"));

//            buttonCloseWelcomeDialog.click();
//            menuBlogs.click();

            JavascriptExecutor js = (JavascriptExecutor) driver; // khai báo đối tượng JavascripExecutor
            js.executeScript("arguments[0].click();", menuBlogs);
      }

      @Test
      public void testJavascriptExecutor02() {
            driver.get("https://cms.anhtester.com/");
            sleep(1);
            // viết theo dạng By, String hoặc WebElement đều được
            //By buttonCloseWelcomeDialog = By.xpath("//button[@data-key='website-popup']");
            WebElement buttonCloseWelcomeDialog = driver.findElement(By.xpath("//button[@data-key='website-popup']"));
            WebElement menuBlogs = driver.findElement(By.xpath("//a[normalize-space()='Blogs']"));
            //a[normalize-space()='View All Categories']
            WebElement buttonViewAllCategories = driver.findElement(By.xpath("//a[normalize-space()='View All Categories']"));

            buttonCloseWelcomeDialog.click();
//            menuBlogs.click();

            JavascriptExecutor js = (JavascriptExecutor) driver; // khai báo đối tượng JavascripExecutor
            WebElement inputSearch = driver.findElement(By.xpath("//input[@id='search']"));
            js.executeScript("arguments[0].setAttribute('value','Qua Tet');", inputSearch);
            sleep(2);
      }

}
