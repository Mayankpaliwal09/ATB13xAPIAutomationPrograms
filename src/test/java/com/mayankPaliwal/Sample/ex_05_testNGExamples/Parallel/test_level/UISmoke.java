package com.mayankPaliwal.Sample.ex_05_testNGExamples.Parallel.test_level;

import org.testng.annotations.Test;

public class UISmoke {

    @Test
    public void test_UI_Smoke(){
        System.out.println("UISmoke");
        System.out.println(Thread.currentThread().getId());
    }

}
