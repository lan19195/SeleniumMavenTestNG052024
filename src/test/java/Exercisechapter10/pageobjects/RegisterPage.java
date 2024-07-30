package Exercisechapter10.pageobjects;

import Exercisechapter10.dataobjects.User;
import Exercisechapter10.helper.ElementUtils;
import Exercisechapter10.helper.Logger;
import Exercisechapter10.helper.Constant;
import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

      private By txtEmail = By.id("email");
      private By txtPassword = By.id("password");
      private By txtConfirmPassword = By.id("confirmPassword");
      private By txtPID = By.id("pid");
      private By btnRegister = By.xpath("//input[@value='Register']");
      private By lblFormErrorMessage = By.xpath("//p[@class='message error']");
      private By lblPWErrorMessage = By.xpath("//li[@class='password']/label[@class='validation-error']");
      private By lblPIDErrorMessage = By.xpath("//li[@class='pid-number']/label[@class='validation-error']");
      private By lblSuccessMessage = By.tagName("h1");
      private By lblActivateMessage = By.xpath("//p[contains(text(),'Registration Confirmed')]");


      public RegisterPage(){
            pageTitle = "Safe Railway = Register an Account";
      }

      public void register(User user){
            Logger.log("Creat user");
//            waitForPageLoad();
            ElementUtils.waitForElementExists(txtEmail, Constant.ELEMENT_WAIT_TIMEOUT);
            var registerButton = ElementUtils.findElement(btnRegister);
            ElementUtils.scrollIntoView(registerButton);

            ElementUtils.findElement(txtEmail).sendKeys(user.getEmail());
            ElementUtils.findElement(txtPassword).sendKeys(user.getPassword());
            ElementUtils.findElement(txtConfirmPassword).sendKeys(user.getPassword());
            ElementUtils.findElement(txtPID).sendKeys(user.getPid());
            registerButton.click();
            try {
                  Thread.sleep(5000);
            } catch (InterruptedException e) {
                  throw new RuntimeException(e);
            }
      }

      public String getFormErrorMessage() {
            try {
                  return ElementUtils.findElement(lblFormErrorMessage).getText().trim();
            } catch (Exception e) {
                  return "";
            }
      }
      public String getPWErrorMessage() {
            try {
                  return ElementUtils.findElement(lblPWErrorMessage).getText().trim();
            } catch (Exception e) {
                  return "";
            }
      }
      public String getPIDErrorMessage() {
            try {
                  return ElementUtils.findElement(lblPIDErrorMessage).getText().trim();
            } catch (Exception e) {
                  return "";
            }
      }

      public String getSuccessMessage() {
            try {
                  return ElementUtils.findElement(lblSuccessMessage).getText().trim();
            } catch (Exception e) {
                  return "";
            }
      }

      public String getActivateMessage() {
            try {
                  return ElementUtils.findElement(lblActivateMessage).getText().trim();
            } catch (Exception e) {
                  return "";
            }
      }


}
