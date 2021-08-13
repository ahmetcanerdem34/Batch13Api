package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest02 {

     /*
    Bir GET Request asagida verilen Endpoint'e yollanir
       https://restful-booker.herokuapp.com/booking
     status code 200'dur
a content type  "application/json" dir.

https://restful-booker.herokuapp.com/booking url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
content type'inin "application/json" oldugunu test edin

https://restful-booker.herokuapp.com/booking/1001 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 404 oldugunu
     */

    @Test
    public void test01(){

        String url="https://restful-booker.herokuapp.com/booking";

        Response response=given().
                accept("application/json").
                when().
                get(url);//neyi kabul  aplicationu json olanlar

        response.prettyPrint();

        //assert işlmlerini ypalım

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        System.out.println(response.getHeaders());
        System.out.println(response.header("Server"));//Cowboy
        System.out.println(response.getStatusCode());
        System.out.println(response.getContentType());
        System.out.println("Tarihe bakalım:"+response.header("Date"));//Wed, 30 Jun 2021 18:05:04 GMT



    }
    @Test
    public void test02(){
     /*
       https://restful-booker.herokuapp.com/booking/1001 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 404 oldugunu
ve Response body'sinin "Not Found" icerdigini
ve Response body'sinin "API" icermedigini test edin
     */
        String url="https://restful-booker.herokuapp.com/booking/1001";
        Response response=given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();
        response.then().assertThat().statusCode(404);
        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("API"));

    }
}
