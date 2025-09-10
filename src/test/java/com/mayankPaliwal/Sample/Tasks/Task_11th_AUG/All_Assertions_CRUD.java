package com.mayankPaliwal.Sample.Tasks.Task_11th_AUG;

import groovy.util.Eval;
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
    Integer bookingid;


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
        bookingid = response.then().extract().path("bookingid");
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

        bookingid = jp.getInt("bookingid");
        System.out.println(bookingid);


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

        vr.body("token",Matchers.notNullValue());

        token = response.then().extract().path("token");

        System.out.println(token);
    }


    @Test(priority = 3 , dependsOnMethods = {"CreateBooking","Token_Create"})
    @Owner("Mayank Paliwal")
    @Description("TC#3 Get Booking by id")
    public void get_Booking(){


        r = RestAssured.given()
                .baseUri(uri)
                .basePath("/booking/"+bookingid)
                .contentType(ContentType.JSON);

        response = r.when().log().all().get();
        vr = response.then().log().all();

        // hamcrest or restassured assertions
        vr.statusCode(200);
        vr.body("firstname",Matchers.equalTo("mayank"));
        vr.body("lastname",Matchers.equalTo("paliwal"));
        vr.body("depositpaid",Matchers.equalTo(true));
        vr.body("totalprice",Matchers.notNullValue());


        //  path extraction assertions of TestNg assertion

        // extraction 1
        String firstname = response.then().extract().path("firstname");
        String lastname = response.then().extract().path("lastname");
        Boolean depositpaid = response.then().extract().path("depositpaid");
        Integer totalprice = response.then().extract().path("totalprice");
        Assert.assertEquals(firstname,"mayank");
        Assert.assertEquals(lastname,"paliwal");
        Assert.assertEquals(depositpaid,true);
        Assert.assertNotNull(totalprice);
        Assert.assertNotNull(firstname);


        // extraction2  using jsonPath
        JsonPath jp = new JsonPath(response.asString());
        String firstname1 = jp.getString("firstname");
        String lastname1 = jp.getString("lastname");
        String totalprice1 = jp.getString("totalprice");
        String depositpaid1 = jp.getString("depositpaid");

//        System.out.println(response.asString());
//        System.out.println(jp.getString("firstname"));
//        System.out.println(firstname1);

        // assertion
        Assert.assertEquals(firstname1,"mayank");
        Assert.assertEquals(lastname1,"paliwal");
        Assert.assertEquals(depositpaid,true);
        Assert.assertNotNull(totalprice1);
        Assert.assertNotNull(firstname1);


        // AssertJ ( 3rd Party library to Assertions)

       assertThat(firstname1).isNotNull().isNotEmpty();
        assertThat(jp.getString("firstname").contains("m"));
        assertThat(jp.getString("firstname")).isNotEmpty();
        assertThat(jp.getString("firstname")).isEqualTo("mayank");
        assertThat(jp.getString("lastname")).isEqualTo("paliwal");
        assertThat(jp.getBoolean("depositpaid")).isTrue();

    }



//  UPDATE - PUT - FULL UPDATE

    @Test(priority = 4 , dependsOnMethods = {"CreateBooking","Token_Create"})
    @Owner("Mayank Paliwal")
    @Description("TC#4 Update Booking by id this is full update using put")
    public void update(){

        String update_Payload =  "{\n" +
                "    \"firstname\" : \"hunny\",\n" +
                "    \"lastname\" : \"paliwal\",\n" +
                "    \"totalprice\" : 100,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-19\",\n" +
                "        \"checkout\" : \"2019-01-11\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"lunch\"\n" +
                "}";


        r = RestAssured.given()
                .baseUri(uri)
                .basePath("/booking/"+bookingid)
                .contentType(ContentType.JSON)
                .cookie("token",token)
                .body(update_Payload);

        response = r.when().log().all().put();

        vr = response.then().log().all();
        vr.statusCode(200);


        vr.body("firstname",Matchers.notNullValue());

        JsonPath jp = new JsonPath(response.asString());
        String firstname = jp.getString("firstname");
        String lastname = jp.getString("lastname");
        Integer totalprice = jp.getInt("totalprice");

        Assert.assertEquals(firstname,"hunny");
        Assert.assertEquals(lastname,"paliwal");
        Assert.assertNotNull(totalprice);


        assertThat(jp.getString("firstname")).isEqualTo("hunny").isNotNull().isNotEmpty();
        assertThat(jp.getString("lastname")).isEqualTo("paliwal").isNotNull().isNotEmpty();
        assertThat(jp.getInt("totalprice")).isNotNull();

    }



    // PARTIAL UPDATE - PATCH

    @Test(priority = 5, dependsOnMethods = {"CreateBooking","Token_Create"})
    @Owner("Mayank Paliwal")
    @Description("TC#5 Partial Update Booking by id ")
    public void partial_update(){
        String Payload =  "{\n" +
                "    \"firstname\" : \"mankuuuuu\",\n" +
                "    \"lastname\" : \"paliwal\"\n" +
                "}";


        r = RestAssured.given()
                .baseUri(uri)
                .basePath("/booking/"+bookingid)
                .contentType(ContentType.JSON)
                .cookie("token",token)
                .body(Payload);

        response = r.when().log().all().patch();

        vr = response.then().log().all();
        vr.statusCode(200);

        JsonPath jp = new JsonPath(response.asString());
        String firstname = jp.getString("firstname");
        String lastname = jp.getString("lastname");

        assertThat(firstname).isNotEmpty().isEqualTo("mankuuuuu");
        Integer totalprice = jp.getInt("totalprice");

        Assert.assertEquals(firstname,"mankuuuuu");
        Assert.assertEquals(lastname,"paliwal");
        Assert.assertNotNull(totalprice);


        assertThat(jp.getString("firstname")).isEqualTo("mankuuuuu").isNotNull().isNotEmpty();
        assertThat(jp.getString("lastname")).isEqualTo("paliwal").isNotNull().isNotEmpty();
        assertThat(jp.getInt("totalprice")).isNotNull();
    }



    @Test(priority = 6, dependsOnMethods = {"CreateBooking","Token_Create"})
    @Owner("Mayank Paliwal")
    @Description("TC#6 Delete booking by Id")
    public void delete_user() {

        r = RestAssured.given()
                .baseUri(uri)
                .basePath("/booking/"+bookingid)
                .contentType(ContentType.JSON)
                .cookie("token",token);

        response = r.when().log().all().delete();

        vr = response.then().log().all();
        vr.statusCode(201);


    }



}










