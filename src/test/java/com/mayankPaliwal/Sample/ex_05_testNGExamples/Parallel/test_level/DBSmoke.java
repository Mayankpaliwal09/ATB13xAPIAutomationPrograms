package com.mayankPaliwal.Sample.ex_05_testNGExamples.Parallel.test_level;

import org.testng.annotations.Test;

public class DBSmoke {
    @Test
    public void test_DB_Smoke(){
        System.out.println("DBSmoke");
        System.out.println(Thread.currentThread().getId());
    }
}
