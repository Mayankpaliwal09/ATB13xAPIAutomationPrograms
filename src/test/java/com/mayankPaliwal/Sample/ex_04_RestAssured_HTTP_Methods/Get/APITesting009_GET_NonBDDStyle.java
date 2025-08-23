package com.mayankPaliwal.Sample.ex_04_RestAssured_HTTP_Methods.Get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting009_GET_NonBDDStyle {
    String pincode ;

    RequestSpecification r;  // pre-request - given()
    Response response;       // making the request - when()
    ValidatableResponse vr;  // post request - then()

    @Test
    public void test_get_NonBDD(){
        pincode = "131001";
        // Part 1  - given()
        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/"+pincode);

        // Part 2 - when()
        response = r.when().log().all().get();


        // Part 3 - then()
        vr = response.then().log().all();
        vr.statusCode(200);
    }

    @Test
    public void test_Get_NonBDD_NegativeTC(){

        pincode = "@";

        // Part 1  - given()

        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/"+pincode);

        // Part 2 - when()
        response = r.when().log().all().get();


        // Part 3 - then()
        vr = response.then().log().all();
        vr.statusCode(404);
    }
}
