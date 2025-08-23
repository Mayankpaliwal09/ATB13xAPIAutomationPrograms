package com.mayankPaliwal.Sample.ex_05_testNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting021_AlwaysRun {

    @Test
    public void test_new_register(){
        System.out.println("user registered");
        Assert.assertTrue(true);
    }

    @Test(alwaysRun = true)
    public void test_login(){
        System.out.println("Succesfully logged In");
        Assert.assertTrue(true);
    }

    @Test
    public void test_dowork(){
        System.out.println("working");
        Assert.assertTrue(true);
    }
}
