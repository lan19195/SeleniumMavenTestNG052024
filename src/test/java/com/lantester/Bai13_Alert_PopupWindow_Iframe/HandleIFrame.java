package com.lantester.Bai13_Alert_PopupWindow_Iframe;

import com.lantester.common.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class HandleIFrame extends BaseTest {
      @Test
      public void testIFrame01() throws InterruptedException {

            driver.navigate().to("https://anhtester.com/contact");
            Thread.sleep(10000);
            System.out.println("iframe total: " + driver.findElements(By.tagName("iframe")).size());
            Thread.sleep(1000);
            //----Switch to content of Messenger--------
            driver.switchTo().frame(0); //Thẻ iframe thứ nhất
            System.out.println(driver.findElement(By.tagName("strong")).getText());

            //----Switch to icon of Messenger---------

            //1. Switch to Parent WindowHandle
            driver.switchTo().parentFrame(); //Chuyển về nội dung chính không thuộc iframe nào

            //2. Switch to iframe icon of Messenger
            driver.switchTo().frame(1); //Thẻ iframe thứ hai
            driver.findElement(By.tagName("svg")).click(); //Nhấn icon để ẩn messenger chat đi
            driver.switchTo().parentFrame(); //Chuyển về nội dung chính không thuộc iframe nào
            Thread.sleep(2000);
      }

      @Test
      public void testIFrameOnVNExpress(){
            driver.get("https://vnexpress.net/");
            sleep(5);
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='google_ads_iframe_/27973503/Vnexpress/Desktop/Large1/Home_0']")));
            sleep(3);
            driver.findElement(By.xpath("//div[contains(@id,'genecy_ifr_')]")).click();
      }

}
