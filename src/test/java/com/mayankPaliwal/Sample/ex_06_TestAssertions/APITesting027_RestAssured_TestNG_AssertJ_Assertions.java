package com.mayankPaliwal.Sample.ex_06_TestAssertions;

import static org.assertj.core.api.Assertions.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;


public class APITesting027_RestAssured_TestNG_AssertJ_Assertions {

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    String token;
    Integer bookingid;

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
                "        \"checkout\" : \"2025-08-02\"\n" +
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
        vr.statusCode(200);

        // Rest Assured -> import org.hamcrest.Matchers; %4-%5
        // Matchers.equalto()

        vr.body("bookingid",Matchers.notNullValue());
        vr.body("booking.firstname",Matchers.equalTo("Mayank"));
        vr.body("booking.lastname",Matchers.equalTo("Paliwal"));
        vr.body("booking.depositpaid",Matchers.equalTo(true));



        // Extraction
        // Concept #1 - Normal( TestNG or Assertj) IS IMP
        bookingid = response.then().extract().path("bookingid");
        String firstName = response.then().extract().path("booking.firstname");
        String lastName = response.then().extract().path("booking.lastname");

        // Concept #2 - (Jsonpath class) Another mechanism to extract the Keys, value by JSON Path
        JsonPath jp = new JsonPath(response.asString());
        String bookingid2 = jp.getString("bookingid");
        System.out.println(bookingid2);

//        assertThat(jp.getInt("bookingid")).isEqualTo(5164);
        assertThat(jp.getString("booking.firstname")).isEqualTo("Mayank");
        assertThat(jp.getString("booking.lastname")).isEqualTo("Paliwal");
        assertThat(jp.getInt("booking.totalprice")).isEqualTo(1111);
        assertThat(jp.getBoolean("booking.depositpaid")).isTrue();
        assertThat(jp.getBoolean("booking.depositpaid")).isTrue();


        // TestNG - Extract the details of the firstname, bookingId, lastname from Response.
        // TestNG Assertions - 75%
        // SoftAssert vs HardAssert (90%)
        // This means that if any assertion fails,
        // the remaining statements in that test method will not be executed.

        Assert.assertEquals(firstName,"Mayank");
        Assert.assertEquals(lastName,"Paliwal");
        Assert.assertNotNull(bookingid);

        if(!firstName.contains("Mayank")){
            Assert.fail("FAIL ho gaya test");
        }




// AssertJ ( 3rd Party library to Assertions) - 20%
// if we are working with testNG or AssertJ  extraction is needed
        assertThat(bookingid).isNotZero().isNotNull().isPositive();
        assertThat(firstName).isEqualTo("Mayank").isNotNull().isNotBlank().isNotEmpty();

        //        assertThat(deposit).isTrue();

    }

}
