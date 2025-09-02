package com.mayankPaliwal.Sample.Tasks.Task_11th_AUG;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.security.PublicKey;
import static org.assertj.core.api.Assertions.*;

//import static org.hamcrest.MatcherAssert.assertThat;


public class All_Assertions_CRUD {

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;
    String uri = "https://restful-booker.herokuapp.com";
    String token ;


    @Test(priority = 1)
    @Owner("Mayank Paliwal")
    @Description("TC#1 Create Booking")
    public void CreateBooking(){

        String payload = "{\n" +
                "    \"firstname\" : \"mayank\",\n" +
                "    \"lastname\" : \"paliwal\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
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

        // Rest Assured Assertions   // hamcrest Matchers
        vr.statusCode(200);
        vr.body("booking.firstname", Matchers.equalTo("mayank"));
        vr.body("booking.lastname", Matchers.equalTo("paliwal"));
        vr.body("booking.depositpaid",Matchers.equalTo(true));


        // Extraction of response both ways
        // there are 2 ways to extract

       // Concept #1 of Extraction normal way
       Integer bookingid = response.then().extract().path("bookingid");
       String firstname = response.then().extract().path("booking.firstname");
       String lastname = response.then().extract().path("booking.lastname");


        // Concept #2 of Extraction i.e  (Jsonpath class) Another mechanism to extract the Keys, value by JSON Path
        JsonPath jp = new JsonPath(response.asString());
        Integer bookingid1 = jp.getInt("bookingid");
        String firstname1 = jp.getString("booking.firstname");
        String lastname1 = jp.getString("booking.lastname");
        System.out.println(bookingid1);
        System.out.println(firstname1);
        System.out.println(lastname1);


        // TESTNG ASSERTIONS

        // Hard assertion of test NG
        Assert.assertEquals(firstname,"mayank","name matched ");
        Assert.assertTrue(bookingid1>0);
        Assert.assertEquals(lastname,"paliwal","lastname matched");

        // Soft assertion
        SoftAssert softAssert = new SoftAssert();
        System.out.println("start of soft assert line");
        softAssert.assertEquals(firstname,"Mayankkkkk");
        System.out.println("end of soft assert line");




        // AssertJ ( 3rd Party library to Assertions) - 20%
        // if we are working with testNG or AssertJ  extraction is needed

        assertThat(bookingid).isNotZero().isNotNull();
        assertThat(jp.getInt("bookingid")).isNotNull();
        assertThat(jp.getString("booking.firstname")).isEqualTo("mayank");
        assertThat(jp.getString("booking.lastname")).isEqualTo("paliwal");
        assertThat(jp.getInt("bookingid")).isNotNull();

    }



    @Test(priority = 2 , dependsOnMethods = {"CreateBooking"})
    @Owner("Mayank Paliwal")
    @Description("TC#2 Auth Creating Token")
    public void Token_Create(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        r = RestAssured.given()
                .baseUri(uri).basePath("/auth").contentType(ContentType.JSON).body(payload);

        response = r.when().log().all().post();

        vr = response.then().log().all();
        vr.statusCode(200);

        token = response.then().extract().path("token");
        System.out.println(token);
    }
}
