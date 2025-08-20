package com.mayankPaliwal.Sample.ex_02_RA_Concepts;

public class APITesting_Lab03_non_builder_pattern {

    // NoDesignPattern

    public void step1() {
        System.out.println("Step 1");
    }

    public void step2() {
        System.out.println("Step 2");
    }

    public void step3(String param1) {
        System.out.println("Step 3");
    }

    public static void main(String[] args) {
        APITesting_Lab03_non_builder_pattern nbp = new APITesting_Lab03_non_builder_pattern();

        // This is a non builder pattern / no chaining
        // we can call randomly also
        nbp.step1();
        nbp.step2();
        nbp.step3("mayank");



        // what and how actually is builder patters
        // it uses dot
        // ex - >  nbp.step1().step2().step3("mayank");  // this is builder pattern or chainig
    }

}
