package com.mayankPaliwal.Sample.ex_03_RA_TestNG_AllureReport;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITesting_Lab06_TestCase {

    String pincode  =  "131001";
    @Test
    // input = 131001  valid pincode
    public void test_tc1_pincode_valid(){


        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
                .when().log().all()
                .get()
                .then()
                .log().all().statusCode(200);
    }


    @Test
    // input = @,#,% special chracter
    public void test_tc2_pincode_Invalid(){
        pincode  =  "@";
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
                .when().log().all()
                .get()
                .then()
                .log().all().statusCode(404);
    }


    @Test
    // input = " " blank
    public void test_tc3_pincode_Invalid(){
        pincode  =  " ";
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
                .when().log().all()
                .get()
                .then()
                .log().all().statusCode(200);
    }
}
