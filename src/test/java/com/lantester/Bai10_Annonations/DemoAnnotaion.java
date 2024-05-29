package com.lantester.Bai10_Annonations;

import com.lantester.common.BaseTest;
import org.testng.annotations.*;

public class DemoAnnotaion extends BaseTest {

      @BeforeSuite
      public void beforeSuite() {
            System.out.println("Before Suite");
      }

      @AfterSuite
      public void afterSuite() {
            System.out.println("After Suite");
      }

      @BeforeTest
      public void beforeTest() {
            System.out.println("Before Test");
      }

      @AfterTest
      public void afterTest() {
            System.out.println("After Test");
      }

      @BeforeClass
      public void beforeClass() {
            System.out.println("Before Class");
      }

      @AfterClass
      public void afterClass() {
            System.out.println("After Class");
      }

      @BeforeGroups(groups = { "testOne" })
      public void beforeGroupOne() {
            System.out.println("Before Group testOne1");
      }

      @AfterGroups(groups = { "testOne" })
      public void afterGroupOne() {
            System.out.println("After Group testOne1");
      }

      @BeforeGroups(groups = { "testTwo" })
      public void beforeGroupTwo() {
            System.out.println("Before Group testTwo");
      }

      @AfterGroups(groups = { "testTwo" })
      public void afterGroupTwo() {
            System.out.println("After Group testTwo");
      }

      @BeforeMethod
      public void beforeMethod() {
            System.out.println("Before Method");
      }

      @AfterMethod
      public void afterMethod() {
            System.out.println("After Method");
      }

      @Test(groups = { "testOne" }, priority = 1, description = "Đây là test case thứ 1")
      public void testOneMethod() {
            System.out.println("Test method 1");
      }

      @Test(groups = { "testTwo" }, priority = 2)
      public void testThreeMethod() {
            System.out.println("Test method 2");
      }

      @Test(groups = { "testOne" }, priority = 3)
      public void testFourMethod() {
            System.out.println("Test method 3");
      }

      @Test(groups = { "testTwo" },priority = 4)
      public void testTwoMethod() {
            System.out.println("Test method 4");
      }
}
