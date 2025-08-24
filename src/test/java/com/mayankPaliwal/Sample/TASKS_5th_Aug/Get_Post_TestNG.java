package com.mayankPaliwal.Sample.TASKS_5th_Aug;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Get_Post_TestNG {



    // GET ping request
    @Test
    public void ping(){


        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")
                .when()
                .get()
                .then().log().all().statusCode(201);
    }


    // Create Booking
    @Test
    public void createBooking(){

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
    }

    // GET booking
    @Test
    public void getBooking(){
        int bookingId = 2666;
       String response =  RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/{id}")
               .pathParam("id", bookingId)

               .accept(ContentType.JSON)
                .when().log().all().get()
                .then().log().all().statusCode(418)
               .extract()
               .asString();

        System.out.println("response "+response);



    }

}
