package com.mayankPaliwal.Sample.ex_02_RA_Concepts;

import io.restassured.RestAssured;

public class APITesting_Lab05_Multiple_TestCase {
    public static void main(String[] args) {

        String pincode  =  "131001";

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
                .when().log().all()
                .get()
                .then()
                .log().all().statusCode(200);

        pincode  =  "@";
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
                .when().log().all()
                .get()
                .then()
                .log().all().statusCode(404);

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
