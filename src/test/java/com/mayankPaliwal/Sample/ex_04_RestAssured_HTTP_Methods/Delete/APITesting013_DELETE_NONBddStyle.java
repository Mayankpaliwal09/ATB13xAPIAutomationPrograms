package com.mayankPaliwal.Sample.ex_04_RestAssured_HTTP_Methods.Delete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting013_DELETE_NONBddStyle {

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

@Test
    public void test_delete_non_bdd(){

        String bookingID = "499";
        String token = "201bd7c0d11aa71";

        r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/"+bookingID);
        r.contentType(ContentType.JSON);
        r.cookie("token",token);
//        r.header("token",token);


        response = r.when().log().all().delete();


        vr = response.then().log().all();
        vr.statusCode(201);


    }
}
