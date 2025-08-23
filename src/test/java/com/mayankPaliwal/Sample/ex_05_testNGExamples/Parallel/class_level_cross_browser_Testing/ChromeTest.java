package com.mayankPaliwal.Sample.ex_05_testNGExamples.Parallel.class_level_cross_browser_Testing;

import org.testng.annotations.Test;

public class ChromeTest {

    @Test
    public void test_Chrome(){
        System.out.println("Chrome is runnning");
        System.out.println(Thread.currentThread().getId());
    }

    public void test_Chrome2(){
        System.out.println("Chrome2 is runnning");
        System.out.println(Thread.currentThread().getId());
    }


}
