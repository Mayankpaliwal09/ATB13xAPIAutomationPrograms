package com.mayankPaliwal.Sample.Homework_5th_august;

import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

public class NBP_using_MAIN {
    public NBP_using_MAIN get(){
        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")
                .when()
                .get()
                .then().log().all().statusCode(201);

        return this;
    }

    public NBP_using_MAIN post(){
        Map<String,String> bookingDates = new HashMap<>();
        bookingDates.put( "checkin","2025-08-25");
        bookingDates.put( "checkout","2025-08-25");

        Map<String,Object> booking = new HashMap<>();
        booking.put("firstname","mayank");
        booking.put("lastname","paliwal");
        booking.put("totalprice",111);
        booking.put("depositpaid",true);
        booking.put("bookingdates",bookingDates);
        booking.put( "additionalneeds" ,"Breakfast");

        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .header("Content-Type","application/json")
                .body(booking)
                .when().log().all().post()
                .then().log().all().statusCode(200);

        return this;
    }
    public static void main(String[] args) {

        NBP_using_MAIN nbp = new NBP_using_MAIN();
        nbp.get();
        nbp.post();

    }
}
