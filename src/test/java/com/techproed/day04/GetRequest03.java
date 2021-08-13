package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest03 {

    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"
ve firstname'in "Sally"
ve lastname'in "Ericsson"
ve checkin date'in 2018-10-07"
ve checkout date'in 2020-09-30 oldugunu test edin
     */
    @Test
    public void test01(){

        String url=" https://restful-booker.herokuapp.com/booking/7";

        Response response=given().accept("application/json").when().get(url);
        response.prettyPrint();
/*
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",Matchers.equalTo("Eric")).
                body("lastname",Matchers.equalTo("Brown")).
                body("totalprice",Matchers.equalTo(225)).
                body("depositpaid",Matchers.equalTo(false)).
                body("bookingdates.checkin",Matchers.equalTo("2019-10-16")).
                body("bookingdates.checkout",Matchers.equalTo("2021-01-15")).
                body("additionalneeds",Matchers.equalTo("Breakfast"));
                //Matcher class-->API testinde doğrulama(equal ile) yaparken 4 class dan biridir

 */

        //tekraralı body yazılarından kurtulmak istiyoruz
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
        body("firstname",equalTo("Sally"),
                "lastname",equalTo("Smith"),
                "totalprice",equalTo(388),
                "depositpaid",equalTo(true),
                "bookingdates.checkin",equalTo("2015-04-28"),
                "bookingdates.checkout",equalTo("2018-09-23"));
    }
}
