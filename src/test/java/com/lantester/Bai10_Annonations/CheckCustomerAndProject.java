package com.lantester.Bai10_Annonations;

import com.lantester.common.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CheckCustomerAndProject extends BaseTest {
      // Mở browser mỗi lần
      @Test(priority = 1)
      public void testCustomerAndProject(){ // dùng cho test intergration
            //Login

            //Add Customer

            //Add Project

            //Search Project

      }

      @Test(priority = 2)
      public void testProjectAndTask(){
            //Login

            //Add Task

            //Add Project

            //Search Project

      }
}
