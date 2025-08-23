package com.mayankPaliwal.Sample.ex_05_testNGExamples;

import org.testng.annotations.Test;

public class APITesting020_TestNG_Enabled {

    @Test
    public void test01(){
        System.out.println("test 1");
    }

    @Test(enabled = false)
    public void test02(){
        System.out.println("test 1");
    }

    @Test
    public void test03(){
        System.out.println("test 1");
    }
}
