package com.lantester.Bai10_Annonations;

import org.testng.annotations.*;

public class DemoAnnotaion2 {

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

      @BeforeGroups(groups = {"testOne"})
      public void beforeGroupOne() {
            System.out.println("Before Group testOne2");
      }

      @AfterGroups(groups = {"testOne"})
      public void afterGroupOne() {
            System.out.println("After Group testOne2");
      }

      @BeforeGroups(groups = {"testTwo"})
      public void beforeGroupTwo() {
            System.out.println("Before Group testTwo");
      }

      @AfterGroups(groups = {"testTwo"})
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

      @Test(groups = {"testOne"}, priority = 1)
      public void testOneMethod() {
            System.out.println("Test method 5");
      }

      @Test(groups = {"testTwo"}, priority = 2)
      public void testThreeMethod() {
            System.out.println("Test method 6");
      }

      @Test(groups = {"testOne"}, priority = 3)
      public void testFourMethod() {
            System.out.println("Test method 7");
      }

      @Test(groups = {"testTwo"}, priority = 4)
      public void testTwoMethod() {
            System.out.println("Test method 8");
      }
}
