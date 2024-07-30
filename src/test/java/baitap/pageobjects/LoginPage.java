package baitap.pageobjects;

import baitap.dataobjects.User;
import baitap.helper.Constant;
import baitap.helper.ElementUtils;
import baitap.helper.Logger;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

      private By txtEmail = By.id("username");
      private By txtPassword = By.id("password");
      private By btnLogin = By.xpath("//input[@value='login']");
      private By lblErrorMessage = By.xpath("//p[contains(@class,'error')]");

      public LoginPage() {
            pageTitle = "Safe Railway - Login";
      }

      public void login(User user) {
            Logger.log("Login user");
//            waitForPageLoad();
            ElementUtils.waitForElementExists(txtEmail, Constant.ELEMENT_WAIT_TIMEOUT);
            if (user.getEmail() != null){
                  var element =  ElementUtils.findElement(txtEmail);
                  element.clear();
                  element.sendKeys(user.getEmail());
            }

            if (user.getPassword() != null){
                  var element =  ElementUtils.findElement(txtPassword);
                  element.clear();
                  element.sendKeys(user.getPassword());
            }
            ElementUtils.findElement(btnLogin).click();
      }

      public String getErrorMessage() {
            try {
                  return ElementUtils.findElement(lblErrorMessage).getText().trim();
            } catch (Exception e) {
                  return "";
            }
      }

}

