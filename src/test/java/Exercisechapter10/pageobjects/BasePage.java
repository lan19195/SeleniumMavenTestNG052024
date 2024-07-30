package Exercisechapter10.pageobjects;

import Exercisechapter10.helper.ElementUtils;
import Exercisechapter10.helper.Logger;
import Exercisechapter10.helper.BrowserUtils;
import Exercisechapter10.helper.Constant;
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
            ElementUtils.waitForElementClickable(locator, Constant.ELEMENT_WAIT_TIMEOUT);
            ElementUtils.findElement(locator).click();
      }

      public boolean isMenuExists(String menu,int timeoutInSeconds) {
            var locator = getMenuLocator(menu);
            return ElementUtils.isElementExits(locator,timeoutInSeconds);
      }

      public void waitForPageLoad(){
            BrowserUtils.waitForTitle("pageTitle", Constant.PAGE_WAIT_TIMEOUT);
      }

      public String getPageTitle(){
            return BrowserUtils.getTitle();
      }


      public void navigateToMailPage(){
            BrowserUtils.switchToTab(Constant.MAILPAGE_URL);
      }

      public String getInfo(By locator){
            return ElementUtils.findElement(locator).getText();
      }

}
