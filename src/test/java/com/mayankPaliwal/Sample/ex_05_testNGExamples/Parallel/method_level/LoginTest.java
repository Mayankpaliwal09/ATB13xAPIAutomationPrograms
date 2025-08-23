package com.mayankPaliwal.Sample.ex_05_testNGExamples.Parallel.method_level;

import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void test_validLogin(){
        System.out.println("ValidLogin - Thread"+ Thread.currentThread().getId());
    }

    @Test
    public void test_InvalidLogin(){
        System.out.println("InValidLogin - Thread"+ Thread.currentThread().getId());
    }
}
