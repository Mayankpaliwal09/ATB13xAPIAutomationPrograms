package com.mayankPaliwal.Sample.Tasks.Task_8th_August;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestFul_Booker_e2e_PUT {


    private static final Logger log = LoggerFactory.getLogger(RestFul_Booker_e2e_PUT.class);
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;


    String uri = "https://restful-booker.herokuapp.com";

    String payload;
    String token;
    String bookingID;



    @Test(priority = 1)
    public void test_01_PingCheck(){

        r = RestAssured.given();
        r.baseUri(uri);
        r.basePath("/ping");


        response = r.when().log().all().get();

        vr = response.then().log().all();
        vr.statusCode(201);
    }



    @Test(priority = 2,alwaysRun = true)
    public void test_02_Auth(){


        payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        r = RestAssured.given();
        r.baseUri(uri);
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payload).log().all();


        response = r.when().log().all().post();

        vr = response.then().log().all();
        vr.statusCode(200);

        token = response.jsonPath().getString("token");
        System.out.println("token"+token);


    }






    @Test(priority = 3 , dependsOnMethods = {"test_02_Auth"})
    public void test_03_Create_booking() {

        payload = "{\n" +
                "    \"firstname\" : \"mayank\",\n" +
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
        r.baseUri(uri);
        r.basePath("/booking");
        r.contentType(ContentType.JSON);
        r.body(payload);

        response = r.when().log().all().post();

        vr = response.then().log().all();
        vr.statusCode(200);

        bookingID = response.jsonPath().getString("bookingid");
        System.out.println("bookingID="+bookingID);

    }



    @Test(priority = 8)
    public void test_04_GET_Allbooking(){

        r = RestAssured.given();
        r.baseUri(uri);
        r.basePath("/booking");
        r.contentType(ContentType.JSON);

        response = r.when().log().all().get();

        vr = response.then().log().all();
        vr.statusCode(200);
    }

    @Test(priority = 5,dependsOnMethods = {"test_02_Auth","test_03_Create_booking"})
    public void test_05_GET_bookingBY_ID(){

        System.out.println(bookingID);
        r = RestAssured.given();
        r.baseUri(uri);
        r.basePath("/booking/"+bookingID);
        r.contentType(ContentType.JSON);

        response = r.when().log().all().get();

        vr = response.then().log().all();
        vr.statusCode(200);
    }



    @Test(priority = 6, dependsOnMethods = {"test_02_Auth","test_03_Create_booking"})
    public void test_06_FullUpdate(){

        payload = "{\n" +
                "    \"firstname\" : \"Sonali Mayank \",\n" +
                "    \"lastname\" : \"Chaurasia\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-07-29\",\n" +
                "        \"checkout\" : \"2025-08-2\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";


        r = RestAssured.given();
        r.baseUri(uri);
        r.basePath("/booking/"+bookingID);
        r.contentType(ContentType.JSON);
        r.cookie("token",token);
        r.body(payload);

        response = r.when().log().all().put();

        vr = response.then().log().all();
        vr.statusCode(200);
    }




    @Test(priority = 7, dependsOnMethods = {"test_03_Create_booking"},alwaysRun = true)
    public void test_07_Delete_Booking(){

        r = RestAssured.given();
        r.baseUri(uri);
        r.basePath("/booking/"+bookingID);
        r.contentType(ContentType.JSON);
        r.cookie("token",token);


        response = r.when().log().all().delete();

        vr = response.then().log().all();
        vr.statusCode(201);
    }



}
