package Exercisechapter10.pageobjects;

import Exercisechapter10.helper.ElementUtils;
import Exercisechapter10.helper.Constant;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
      private By lblWelcomeMessage = By.tagName("h1");
      private By linkCreatAccount = By.xpath("//a[normalize-space()='create an account']");

      public HomePage(){
            pageTitle = "Safe Railway - Selenium Automation";
      }

      public String getWelcomeMessage(){
//            waitForPageLoad();
            ElementUtils.waitForElementExists(lblWelcomeMessage, Constant.ELEMENT_WAIT_TIMEOUT);
            return ElementUtils.findElement(lblWelcomeMessage).getText();
      }

      public void creatNewAccount(){
            ElementUtils.clickElement(linkCreatAccount);
      }
}

