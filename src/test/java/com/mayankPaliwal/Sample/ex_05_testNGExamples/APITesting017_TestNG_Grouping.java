package com.mayankPaliwal.Sample.ex_05_testNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting017_TestNG_Grouping {

    // reg -> all 3 tc will run
    // snaity -> sanity run - i.e 1
    // smoke  -> smoke run - i.e 1


    @Test(groups = {"reg","sanity"})
    public void test_SanityRun(){
        System.out.println("This is Sanity Testing");
        System.out.println("QA");
        Assert.assertTrue(true);
    }

    @Test(groups = {"reg"})
    public void test_RegressionRun(){
        System.out.println("This is Regression Testing");
        Assert.assertTrue(false);
    }

    @Test(groups = {"reg","smoke"})
    public void test_SmokeRun(){
        System.out.println("This is Smoke Testing");
        Assert.assertTrue(true);
    }
}
