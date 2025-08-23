package com.mayankPaliwal.Sample.ex_05_testNGExamples.Parallel.test_level;

import org.testng.annotations.Test;

public class APISmoke {
    @Test
    public void test_API_Smoke(){
        System.out.println("APISmoke");
        System.out.println(Thread.currentThread().getId());
    }
}
