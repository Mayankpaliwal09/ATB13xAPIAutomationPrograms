package com.mayankPaliwal.Sample.ex_07_Payload_Managment.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class APITesting028_RestAssured_Payload_Map {

    RequestSpecification r;
    ValidatableResponse vr;
    Response response;
    String token;
    Integer bookingId;

    @Test
    public void test_POST() {
//    String payload_POST = "{\n" +
//            "    \"firstname\" : \""+name+"\",\n" +
//            "    \"lastname\" : \"Dutta\",\n" +
//            "    \"totalprice\" : 123,\n" +
//            "    \"depositpaid\" : false,\n" +
//            "    \"bookingdates\" : {\n" +
//            "        \"checkin\" : \"2024-01-01\",\n" +
//            "        \"checkout\" : \"2024-01-01\"\n" +
//            "    },\n" +
//            "    \"additionalneeds\" : \"Lunch\"\n" +
//            "}";
//    // .....
//


        // Hashmap -> key and value pair
        // Parent Hashmap ->  key and value , Child Hashmap
        // here we are using LinkedHashMap bcoz we have to maintain order

        LinkedHashMap <String, Object> payload = new LinkedHashMap<>();

        payload.put("firstname","mayank");
        payload.put("lastname","paliwal");
        payload.put("totalprice",1111);
        payload.put("depositpaid",false);

        // now for bookingdates we use child map
        Map<String, Object> bookingDatesMap = new LinkedHashMap<>();
        bookingDatesMap.put("checkin","2025-08-22");
        bookingDatesMap.put("checkout","2025-08-25");

        payload.put("bookingdates",bookingDatesMap);
        payload.put("additionalneeds","breakfast");

        System.out.println(payload);



        r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com/");
        r.basePath("booking");
        r.contentType(ContentType.JSON);
        r.body(payload).log().all();


        response = r.when().log().all().post();

        // Get Validatable response to perform validation
        vr = response.then().log().all();
        vr.statusCode(200);

        // Rest Assured -> import org.hamcrest.Matchers; %4-%5
        // Matchers.equalto()

        vr.body("booking.firstname", Matchers.equalTo("mayank"));
        vr.body("booking.lastname",Matchers.equalTo("paliwal"));
        vr.body("booking.lastname",Matchers.containsString("p"));
        vr.body("booking.depositpaid", Matchers.equalTo(false));
        vr.body("bookingid", Matchers.notNullValue());





    }
}
