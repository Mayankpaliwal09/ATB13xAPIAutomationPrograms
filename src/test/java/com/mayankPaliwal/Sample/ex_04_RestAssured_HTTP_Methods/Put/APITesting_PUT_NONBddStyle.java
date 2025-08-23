package com.mayankPaliwal.Sample.ex_04_RestAssured_HTTP_Methods.Put;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_PUT_NONBddStyle {

    // PUT

    // token, booking ID - A
    // public void get_token(){ }
    // public void get_booking_id(){}

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    @Test
    public void test_put_non_bdd(){

        String bookingid = "499";
        String token = "201bd7c0d11aa71";

        String payload = " {\n" +
                "    \"firstname\" : \"Mayank sonal \",\n" +
                "    \"lastname\" : \"Paliwal\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-07-29\",\n" +
                "        \"checkout\" : \"2025-08-2\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/"+bookingid);
        r.contentType(ContentType.JSON);
//        r.header("cookie","token="+token);
        r.cookie("token",token);
        r.body(payload).log().all();


        response = r.when().log().all().put();

        vr = response.then().log().all();
        vr.statusCode(200);
        // we have not verified the response, we have only verified the status code.

    }


}
