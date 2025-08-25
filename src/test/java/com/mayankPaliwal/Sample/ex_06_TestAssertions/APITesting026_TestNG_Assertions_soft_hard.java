package com.mayankPaliwal.Sample.ex_06_TestAssertions;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITesting026_TestNG_Assertions_soft_hard {


    // HARD ASSERTION  - Execution stops where assertions fail program will not execute further

    @Test
    public void test_hardAssertionExample(){

        System.out.println("Start of the program ");
        Assert.assertEquals("Mayank","mayank");
        System.out.println("End of the program");

    }



    // SOFT ASSERTION
    @Test
    public void test_softAssertionExample(){

        SoftAssert softAssert = new SoftAssert();
        System.out.println("Start of the program ");
        softAssert.assertEquals("Mayank","mayank");
        System.out.println("End of the program");
        softAssert.assertAll();

    }
}
