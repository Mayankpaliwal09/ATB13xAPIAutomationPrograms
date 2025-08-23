package com.mayankPaliwal.Sample.ex_05_testNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting018_TestNG_DependsOnMethod {

    @Test
    public void serverStartedOK(){
        System.out.println("I will run first");
        System.out.println("Server is Started");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "serverStartedOK" )
    public void test1(){
        System.out.println("method 1");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "serverStartedOK" )
    public void test2(){
        System.out.println("method 2");
        Assert.assertTrue(true);
    }
}
