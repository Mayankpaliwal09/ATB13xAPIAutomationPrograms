package com.mayankPaliwal.Sample.ex_05_testNGExamples;

import org.testng.annotations.Test;

public class APITesting022_TestNG_invocationCount {

    @Test(invocationCount = 2)
    public void tc1(){
        System.out.println("tc1");
    }

    @Test(invocationCount = 4)
    public void tc2(){
        System.out.println("tc1");
    }
}
