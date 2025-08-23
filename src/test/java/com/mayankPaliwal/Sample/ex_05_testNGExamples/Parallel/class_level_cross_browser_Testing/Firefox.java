package com.mayankPaliwal.Sample.ex_05_testNGExamples.Parallel.class_level_cross_browser_Testing;

import org.testng.annotations.Test;

public class Firefox {
    @Test
    public void test_firefox(){
        System.out.println("Firefox is runnning");
        System.out.println(Thread.currentThread().getId());
    }
}
