package com.mayankPaliwal.Sample.ex_06_TestAssertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;

public class APITesting025_RestAsssured_Assertions {

    // Create booking test Assertions


    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    String token;
    String bookinID;




    @Test
    public void test_CreateBooking_POST(){

        // payload
        // given - setting up the body , url , path
        // when  - making the request
        // then - validation part


       String payload = "{\n" +
               "    \"firstname\" : \"Mayank\",\n" +
               "    \"lastname\" : \"Paliwal\",\n" +
               "    \"totalprice\" : 1111,\n" +
               "    \"depositpaid\" : true,\n" +
               "    \"bookingdates\" : {\n" +
               "        \"checkin\" : \"2025-07-29\",\n" +
               "        \"checkout\" : \"2025-08-2\"\n" +
               "    },\n" +
               "    \"additionalneeds\" : \"Breakfast\"\n" +
               "}";

       r = RestAssured.given();
       r.baseUri("https://restful-booker.herokuapp.com");
       r.basePath("/booking");

       // header
       r.contentType(ContentType.JSON);
       r.body(payload).log().all();


       response = r.when().log().all().post();


       // Get the Validate response to perform Validation
       vr = response.then().log().all();

       // Rest Assured.  Assertions
       vr.statusCode(200);

        //  Boooking ID !=null , firstname == Pramod
        //  Extract the response body and do it

//        System.out.println(response.asString());

       vr.body("bookingid",Matchers.notNullValue());
       vr.body("booking.firstname",Matchers.equalTo("Mayank"));
       vr.body("booking.firstname",Matchers.equalTo("Mayank"));
       vr.body("booking.lastname",Matchers.equalTo("Paliwal"));
       vr.body("booking.depositpaid",Matchers.equalTo(true));


    }
}
