package baitap.pageobjects;

import baitap.helper.BrowserUtils;
import baitap.helper.Constant;
import baitap.helper.ElementUtils;
import baitap.helper.Logger;
import org.openqa.selenium.By;

public class BasePage {
      protected String pageTitle;

      private String xpathMenu = "//li[.='%s']";

      private By getMenuLocator(String menu){
            return By.xpath(String.format(xpathMenu,menu));
      }

      public void selectMenu(String menu){
            Logger.log("Select"+ menu);
            var locator = getMenuLocator(menu);
            ElementUtils.waitForElementClickable(locator,Constant.ELEMENT_WAIT_TIMEOUT);
            ElementUtils.findElement(locator).click();
      }
      public boolean isMenuExists(String menu,int timeoutInSeconds) {
            var locator = getMenuLocator(menu);
            return ElementUtils.isElementExits(locator,timeoutInSeconds);
      }

      public void waitForPageLoad(){
            BrowserUtils.waitForTitle("pageTitle", Constant.PAGE_WAIT_TIMEOUT);
      }

}
