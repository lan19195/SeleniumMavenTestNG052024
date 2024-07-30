package Exercisechapter10.testcases;

import Exercisechapter10.dataobjects.BookTicketForm;
import Exercisechapter10.dataobjects.ChangePWForm;
import Exercisechapter10.dataobjects.Menu;
import Exercisechapter10.dataobjects.User;
import Exercisechapter10.pageobjects.*;
import Exercisechapter10.pageobjects.*;
import Exercisechapter10.helper.BrowserUtils;
import Exercisechapter10.helper.Constant;
import Exercisechapter10.helper.DateTimeUtils;
import Exercisechapter10.pageobjects.*;
import Exercisechapter10.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCase extends TestBase {
      @Test
      public void TestCase001() {

            //0. Prepare data
            var user = new User("phuonglan5458@gmail.com", "12345678a");
            var expectedMessage = "Welcome to Safe Railway";


            //1. Navigate to QA Railway Website
            //2. Click on "Login" tab
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());

            //3. Enter valid Email and Password
            //4. Click on "Login" button
            LoginPage loginPage = new LoginPage();
            loginPage.login(user);

            //VP: User is logged into Railway
            Assert.assertTrue(homePage.isMenuExists(Menu.LOGOUT.toString(), Constant.ELEMENT_WAIT_TIMEOUT));

            //VP: Welcome user message is displayed.
            Assert.assertEquals(homePage.getWelcomeMessage(), expectedMessage);
      }

      @DataProvider(name = "loginTestData", parallel = true)
      public Object[][] createData002() {
            return new Object[][]{
                    {new User(null, "Test123"), "There was a problem with your login and/or errors exist in your form."},
                    {new User("phuonglan5458@gmail.com", "Test123"), "Invalid username or password. Please try again."}
            };
      }


      @Test(dataProvider = "loginTestData")
      public void TestCase002(User user, String expectedErrorMessage) {
            //1. Navigate to QA Railway Website
            //2. Click on "Login" tab
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());

            //3. Enter credential and login
            LoginPage loginPage = new LoginPage();
            loginPage.login(user);

            //VP: User can't login and error message exist appears
            Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);

      }

      @Test
      public void TestCase004() {
            //0. Prepare data
            var user = new User("phuonglan5458@gmail.com", "1234");
            var expectedErrorMessage = "Invalid username or password. Please try again.";
            var after4TimesExpectedErrorMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

            //1. Navigate to QA Railway Website
            //2. Click on "Login" tab
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());
            LoginPage loginPage = new LoginPage();

            //5. Repeat step login three more times.
            for (int i = 0; i < 4; i++) {
                  //3. Enter valid information into "Username" textbox except "Password" textbox.
                  //4. Click on "Login" button
                  loginPage.login(user);

                  if (i == 0)
                        //VP: User can't login and error message exist appears
                        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
            }
            //VP: You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.
            Assert.assertEquals(loginPage.getErrorMessage(), after4TimesExpectedErrorMessage);
      }

      @Test
      public void TestCase005() {
            //0.1 Prepare data
            var emailTemplate = "test%s@test.com";
            var dynamicPattern = "yyyymmddhhmmss";
            var email = String.format(emailTemplate, DateTimeUtils.getCurrentDateTime(dynamicPattern));
            var user = new User(email, "Test123@", "123456789");
            var expectedErrorMessage = "Invalid username or password. Please try again.";

            //0.2 Precondition: Creat new not-active account
            // Navigate to Register page
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.REGISTER.toString());

            //Register new account
            RegisterPage registerPage = new RegisterPage();
            registerPage.register(user);

            //1. Navigate to QA Railway Website
            //2. Click on "Login" tab
            homePage.selectMenu(Menu.LOGIN.toString());

            //3. Enter username and password of account hasn't been activated.
            //4. Click on "Login" button
            LoginPage loginPage = new LoginPage();
            loginPage.login(user);

            //VP: User can't login and message "Invalid username or password. Please try again." appears.
            Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
      }

      @Test
      public void TestCase006() {
            //0. Prepare data
            var user = new User("phuonglan5458@gmail.com", "12345678a");

            //1. Navigate to QA Railway Website
            //2. Login with valid Email and Password
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());
            LoginPage loginPage = new LoginPage();
            loginPage.login(user);

            //3. Click on "FAQ" tab
            loginPage.selectMenu(Menu.FAQ.toString());
            //4. Click on "Log out" tab
            loginPage.selectMenu(Menu.LOGOUT.toString());

            //VP: Home page displays."Log out" tab is disappeared.
            Assert.assertEquals(BrowserUtils.getPageURL(), Constant.URL_HOMEPAGE);
            LogoutPage logoutPage = new LogoutPage();
            Assert.assertTrue(logoutPage.LogoutPage());
      }

      @Test
      public void TestCase007() {
            //0. Pre-condition
            var user = new User("phuonglan5458@gmail.com", "12345678a", "123456789");
            var expectedFormErrorMessage = "This email address is already in use.";

            //1. Navigate to QA Railway Website
            //2. Click on "Register" tab
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.REGISTER.toString());

            //3. Enter information of the created account in Pre-condition
            //4. Click on "Register" button
            RegisterPage registerPage = new RegisterPage();
            registerPage.register(user);

            //VP: Error message "This email address is already in use." displays above the form.
            Assert.assertEquals(registerPage.getFormErrorMessage(), expectedFormErrorMessage);
      }

      @Test
      public void TestCase008() {
            //0. Pre-condition
            var user = new User("phuonglan5458@gmail.com", "", "");
            var expectedFormErrorMessage = "There're errors in the form. Please correct the errors and try again.";
            var expectedPWErrorMessage = "Invalid password length.";
            var expectedPIDErrorMessage = "Invalid ID length.";

            //1. Navigate to QA Railway Website
            //2. Click on "Register" tab
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.REGISTER.toString());

            //3. Enter information of the created account in Pre-condition
            //4. Click on "Register" button
            RegisterPage registerPage = new RegisterPage();
            registerPage.register(user);

            //VP: Message "There're errors in the form. Please correct the errors and try again." appears above the form.
            Assert.assertEquals(registerPage.getFormErrorMessage(), expectedFormErrorMessage);
            //VP: Next to password fields, error message "Invalid password length." displays
            Assert.assertEquals(registerPage.getPWErrorMessage(), expectedPWErrorMessage);
            //VP: Next to PID field, error message "Invalid ID length." displays
            Assert.assertEquals(registerPage.getPIDErrorMessage(), expectedPIDErrorMessage);
      }

      @Test
      public void TestCase009() {
            //0.1 Prepare data
            var emailTemplate = "test%s@sharklasers.com";
            var dynamicPattern = "ddhhmmss";
            var email = String.format(emailTemplate, DateTimeUtils.getCurrentDateTime(dynamicPattern));
            var user = new User(email, "12345678a", "123456789");
            var expectedRegisterMessage = "Thank you for registering your account";
            var expectedActivateMessage = "Registration Confirmed! You can now log in to the site.";

            //1. Navigate to QA Railway Website
            //VP: Home page is shown with guide containing href "create an account" to "Register" page
            //2. Click on "Create an account"
            //VP: Register page is shown
            HomePage homePage = new HomePage();
            homePage.creatNewAccount();

            //3. Enter valid information into all fields
            //4. Click on "Register" button
            //VP: "Thank you for registering your account" is shown
            RegisterPage registerPage = new RegisterPage();
            registerPage.register(user);
            Assert.assertEquals(registerPage.getSuccessMessage(), expectedRegisterMessage);

            //5. Get email information (webmail address, mailbox and password) and navigate to that webmail
            registerPage.navigateToMailPage();

            //7. Open email with subject containing "Please confirm your account"  and the email of the new account at step 3
            //8. Click on the activate link
            MailPage mailPage = new MailPage();
            mailPage.creatEmail(user);
            mailPage.confirmEmail();

            //VP: Redirect to Railways page and message "Registration Confirmed! You can now log in to the site." is shown
            BrowserUtils.switchTabs("thirdTab");
            Assert.assertEquals(registerPage.getActivateMessage(), expectedActivateMessage);
      }

      @Test
      public void TestCase010() {
            //0.1 Prepare data
            //0.2 Pre-condition: an actived account is existing
            var user = new User("test17102707@sharklasers.com", "12345678a");
            var changePWForm = new ChangePWForm("12345678a", "12345678a");
            var expectedFormTile = "Password Change Form";
            var expectedErrorMessage = "The new password cannot be the same with the current password";

            //1. Navigate to QA Railway Login page
            //2. Click on "Forgot Password page" link
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());
            LoginPage loginPage = new LoginPage();
            loginPage.clickForgotPW();

            //3. Enter the email address of the activated account
            //4. Click on "Send Instructions" button
            ForgotPWPage forgotPWPage = new ForgotPWPage();
            forgotPWPage.setForgotEmail(user);

            //5. Login to the mailbox (the same mailbox when creating account)
            forgotPWPage.navigateToMailPage();

            //6. Open email with subject contaning "Please reset your password" and the email of the account at step 3
            //7. Click on reset link
            MailPage mailPage = new MailPage();
            mailPage.creatEmail(user);
            mailPage.resetPWEmail();
            //VP: Redirect to Railways page and Form "Password Change Form" is shown with the reset password token
            BrowserUtils.switchTabs("thirdTab");
            PWChangeForm pwChangeForm = new PWChangeForm();
            Assert.assertEquals(pwChangeForm.getFormTitle(), expectedFormTile);
            Assert.assertTrue(pwChangeForm.isResetTokenDisplay());

            //8. Input same password into 2 fields  "new password" and "confirm password"
            //9. Click Reset Password
            //VP: Message "The new password cannot be the same with the current password" is shown
            pwChangeForm.changePW(changePWForm);
            Assert.assertEquals(pwChangeForm.getReturnMessage(), expectedErrorMessage);
      }

      @Test
      public void TestCase011() {
            //0.1 Prepare data
            //0.2 Pre-condition: an actived account is existing
            var user = new User("test17102707@sharklasers.com", "12345678a");
            var changePWForm = new ChangePWForm("12345678a", "123456789a");
            var expectedFormTile = "Password Change Form";
            var expectedFormErrorMessage = "Could not reset password. Please correct the errors and try again.";
            var expectedPWErrorMessage = "The password confirmation did not match the new password.";

            //1. Navigate to QA Railway Login page
            //2. Click on "Forgot Password page" link
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());
            LoginPage loginPage = new LoginPage();
            loginPage.clickForgotPW();

            //3. Enter the email address of the activated account
            //4. Click on "Send Instructions" button
            ForgotPWPage forgotPWPage = new ForgotPWPage();
            forgotPWPage.setForgotEmail(user);

            //5. Login to the mailbox (the same mailbox when creating account)
            forgotPWPage.navigateToMailPage();

            //6. Open email with subject contaning "Please reset your password" and the email of the account at step 3
            //7. Click on reset link
            MailPage mailPage = new MailPage();
            mailPage.creatEmail(user);
            mailPage.resetPWEmail();
            //VP: Redirect to Railways page and Form "Password Change Form" is shown with the reset password token
            BrowserUtils.switchTabs("thirdTab");
            PWChangeForm pwChangeForm = new PWChangeForm();
            Assert.assertEquals(pwChangeForm.getFormTitle(), expectedFormTile);
            Assert.assertTrue(pwChangeForm.isResetTokenDisplay());

            //8. Input same password into 2 fields  "new password" and "confirm password"
            //9. Click Reset Password
            //VP: Error message "Could not reset password. Please correct the errors and try again." displays above the form.
            //VP: Error message "The password confirmation did not match the new password." displays next to the confirm password field.
            pwChangeForm.changePW(changePWForm);
            Assert.assertEquals(pwChangeForm.getReturnMessage(), expectedFormErrorMessage);
            Assert.assertEquals(pwChangeForm.getValidateErrorMessage(), expectedPWErrorMessage);
      }


      @Test
      public void TestCase012() {
            //0. Prepare data
            //0.1 Pre-condition: an actived account is existing
            var user = new User("phuonglan5458@gmail.com", "12345678a");
            var expectedMessage = "Ticket booked successfully!";
            var addDays = 12;
            var bookTicketForm = new BookTicketForm(addDays, "Nha Trang", "Huế", "Soft bed with air conditioner", "1");

            //1. Navigate to QA Railway Website
            //2. Login with a valid account
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());
            LoginPage loginPage = new LoginPage();
            loginPage.login(user);

            //3. Click on "Book ticket" tab
            loginPage.selectMenu(Menu.BOOKTICKET.toString());

            //4. Select the next 12 days from "Depart date"
            //5. Select Depart from "Nha Trang" and Arrive at "Huế"
            //6. Select "Soft bed with air conditioner" for "Seat type"
            //7. Select "1" for "Ticket amount"
            //8. Click on "Book ticket" button
            BookTicketPage bookTicketPage = new BookTicketPage();
            bookTicketPage.bookTickets(bookTicketForm);

            //VP: Message "Ticket booked successfully!" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount
            Assert.assertEquals(bookTicketPage.getMessage(), expectedMessage);
            Assert.assertEquals(bookTicketPage.getDepartmentDate(addDays), bookTicketPage.returnDepartDate());
            Assert.assertEquals(bookTicketForm.getDepartFrom(), bookTicketPage.returnDepartStation());
            Assert.assertEquals(bookTicketForm.getArriveAt(), bookTicketPage.returnArriveStation());
            Assert.assertEquals(bookTicketForm.getSeatType(), bookTicketPage.returnSeatType());
            Assert.assertEquals(bookTicketForm.getTicketAmounts(), bookTicketPage.returnAmounts());
      }

      @Test
      public void TestCase013() {
            //0. Prepare data
            //0.1 Pre-condition: an actived account is existing
            var user = new User("phuonglan5458@gmail.com", "12345678a");
            var expectedMessage = "Ticket booked successfully!";
            var addDays = 25;
            var bookTicketForm = new BookTicketForm(addDays, "Nha Trang", "Sài Gòn", "Soft seat with air conditioner", "5");

            //1. Navigate to QA Railway Website
            //2. Login with a valid account
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());
            LoginPage loginPage = new LoginPage();
            loginPage.login(user);

            //3. Click on "Book ticket" tab
            loginPage.selectMenu(Menu.BOOKTICKET.toString());

            //4. Select the next 25 days from "Depart date"
            //5. Select "Nha Trang" for "Depart from" and "Sài Gòn" for "Arrive at".
            //6. Select "Soft seat with air conditioner" for "Seat type"
            //7. Select "5" for "Ticket amount"
            //8. Click on "Book ticket" button
            BookTicketPage bookTicketPage = new BookTicketPage();
            bookTicketPage.bookTickets(bookTicketForm);

            //VP: Message "Ticket booked successfully!" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount
            Assert.assertEquals(bookTicketPage.getMessage(), expectedMessage);
            Assert.assertEquals(bookTicketPage.getDepartmentDate(addDays), bookTicketPage.returnDepartDate());
            Assert.assertEquals(bookTicketForm.getDepartFrom(), bookTicketPage.returnDepartStation());
            Assert.assertEquals(bookTicketForm.getArriveAt(), bookTicketPage.returnArriveStation());
            Assert.assertEquals(bookTicketForm.getSeatType(), bookTicketPage.returnSeatType());
            Assert.assertEquals(bookTicketForm.getTicketAmounts(), bookTicketPage.returnAmounts());
      }

      @Test
      public void TestCase014() {
            //0. Prepare data
            //0.1 Pre-condition: an actived account is existing
            var user = new User("phuonglan5458@gmail.com", "12345678a");
            var departStation = "Đà Nẵng";
            var arriveStation = "Sài Gòn";
            var expectedTitle = "Ticket price from Đà Nẵng to Sài Gòn";
            var HS = "310000";
            var SS = "335000";
            var SSC = "360000";
            var HB = "410000";
            var SB = "460000";
            var SBC = "510000";
            var expectedPrice = "Price (VND) " + HS + " " + SS + " " + SSC + " " + HB + " " + SB + " " + SBC;

            //1. Navigate to QA Railway Website
            //2. Login with a valid account
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());
            LoginPage loginPage = new LoginPage();
            loginPage.login(user);

            //3. Click on "Timetable" tab
            loginPage.selectMenu(Menu.TIMETABLE.toString());
            //4. Click on "check price" link of the route from "Đà Nẵng" to "Sài Gòn"
            TimeTablePage timeTablePage = new TimeTablePage();
            timeTablePage.checkPrice(departStation, arriveStation);
            //VP: """Ticket Price"" page is loaded.Ticket table shows ""Ticket price from Đà Nẵng to Sài Gòn"".
            //Price for each seat displays correctly: HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000"
            TicketPricePage ticketPricePage = new TicketPricePage();
            Assert.assertEquals(ticketPricePage.getTitle(), expectedTitle);
            Assert.assertEquals(ticketPricePage.getTotalPrice(), expectedPrice);
      }

      @Test
      public void TestCase015() {
            //0. Prepare data
            //0.1 Pre-condition: an actived account is existing
            var user = new User("phuonglan5458@gmail.com", "12345678a");
            var departStation = "Quảng Ngãi";
            var arriveStation = "Huế";
            var expectedTitle = "Ticket price from Đà Nẵng to Sài Gòn";
            var expectedMessage = "Ticket booked successfully!";
            var addDays = 10;
            var bookTicketForm = new BookTicketForm(addDays, departStation, arriveStation, "Hard seat", "5");

            //1. Navigate to QA Railway Website
            //2. Login with a valid account
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());
            LoginPage loginPage = new LoginPage();
            loginPage.login(user);

            //3. Click on "Timetable" tab
            loginPage.selectMenu(Menu.TIMETABLE.toString());
            //4. Click on book ticket of route "Quảng Ngãi" to "Huế"
            //VP: Book ticket form is shown with the corrected "depart from" and "Arrive at"
            TimeTablePage timeTablePage = new TimeTablePage();
            timeTablePage.bookTicket(departStation, arriveStation);
            BookTicketPage bookTicketPage = new BookTicketPage();
            Assert.assertEquals(bookTicketPage.getSelectedDepartFrom(), departStation);
            Assert.assertEquals(bookTicketPage.getSelectedArriveAt(), arriveStation);

            //5. Select Depart date next 10 days
            //6. Select Ticket amount = 5
            //7. Click on "Book ticket" button
            bookTicketPage.bookTickets(bookTicketForm);

            //VP: Message "Ticket booked successfully!" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount
            Assert.assertEquals(bookTicketPage.getMessage(), expectedMessage);
            Assert.assertEquals(bookTicketPage.getDepartmentDate(addDays), bookTicketPage.returnDepartDate());
            Assert.assertEquals(bookTicketForm.getDepartFrom(), bookTicketPage.returnDepartStation());
            Assert.assertEquals(bookTicketForm.getArriveAt(), bookTicketPage.returnArriveStation());
            Assert.assertEquals(bookTicketForm.getSeatType(), bookTicketPage.returnSeatType());
            Assert.assertEquals(bookTicketForm.getTicketAmounts(), bookTicketPage.returnAmounts());
      }

      @Test
      public void TestCase016() {
            //0. Prepare data
            //0.1 Pre-condition: an actived account is existing
            var user = new User("test17102707@sharklasers.com", "12345678a");
            var expectedMessage = "Ticket booked successfully!";
            var addDays = 25;
            var bookTicketForm = new BookTicketForm(addDays, "Nha Trang", "Sài Gòn", "Hard seat", "1");

            //1. Navigate to QA Railway Website
            //2. Login with a valid account
            HomePage homePage = new HomePage();
            homePage.selectMenu(Menu.LOGIN.toString());
            LoginPage loginPage = new LoginPage();
            loginPage.login(user);

            //3. Book a ticket
            loginPage.selectMenu(Menu.BOOKTICKET.toString());
            BookTicketPage bookTicketPage = new BookTicketPage();
            bookTicketPage.bookTickets(bookTicketForm);
            Assert.assertEquals(bookTicketPage.getMessage(), expectedMessage);

            //4. Click on "My ticket" tab
            bookTicketPage.selectMenu(Menu.MYTICKET.toString());
            //5. Click on "Cancel" button of ticket which user want to cancel.
            //6. Click on "OK" button on Confirmation message "Are you sure?"
            MyTicketPage myTicketPage = new MyTicketPage();
            myTicketPage.getNumberOfTableBefore();
            var expectedNumberOfTableAfter = myTicketPage.getNumberOfTableBefore()-1;
            myTicketPage.cancelTicket();
            Assert.assertEquals(myTicketPage.getNumberOfTableBefore(),expectedNumberOfTableAfter);
      }
}
