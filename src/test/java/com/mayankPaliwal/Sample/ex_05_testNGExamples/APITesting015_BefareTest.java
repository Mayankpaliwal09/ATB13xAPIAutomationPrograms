package com.mayankPaliwal.Sample.ex_05_testNGExamples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITesting015_BefareTest {


    // PUT Request
    // 1. getToken
    // 2. getBookingId
    // 3. test_PUT ( which will use the above two methods)
    // 4. closeAllThings

    // so before sending request we need 2 function that will execute let suppose
    // token() - gives token , bookingID() - gives id

    @BeforeTest
    public void getToken(){
        System.out.println("Before GET token");
    }

    @BeforeTest
    public void getBookingID(){
        System.out.println("Before GET booking");
    }

   @Test
    public void test_PUT(){
     // token , bookingID
       System.out.println("PUT REQUEST");
    }

     @AfterTest
    public void closeAllThings(){
        // token , bookingID
        System.out.println("close");
    }
}
