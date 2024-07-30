package Exercisechapter10.pageobjects;

import Exercisechapter10.helper.Logger;
import Exercisechapter10.dataobjects.User;
import Exercisechapter10.helper.Constant;
import Exercisechapter10.helper.ElementUtils;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

      private By txtEmail = By.id("username");
      private By txtPassword = By.id("password");
      private By btnLogin = By.xpath("//input[@value='login']");
      private By lblErrorMessage = By.xpath("//p[contains(@class,'error')]");
      private By linkForgotPW = By.xpath("//a[normalize-space()='Forgot Password page']");

      public LoginPage() {
            pageTitle = "Safe Railway - Login";
      }

      public void login(User user) {
            Logger.log("Login user");
//            waitForPageLoad();
            ElementUtils.waitForElementExists(txtEmail, Constant.ELEMENT_WAIT_TIMEOUT);
            if (user.getEmail() != null){
                  var element =  ElementUtils.findElement(txtEmail);
                  element.sendKeys(user.getEmail());
            }

            if (user.getPassword() != null){
                  var element =  ElementUtils.findElement(txtPassword);
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

      public void clickForgotPW(){
            ElementUtils.clickElement(linkForgotPW);
      }

}

