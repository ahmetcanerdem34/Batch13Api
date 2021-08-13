package com.Z_apiPractice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class practice01{
    /*
         Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yolladim
         https://restful-booker.herokuapp.com/booking/10
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Mark"
     And lastname "Wilson"
     And depositpaid true
     And checkin date "2016-06-19"
     And checkout date "2019-08-26"
     1.Adim Url'i olustur
     2.Adim Data'yi oluştur
     3.Adim Request gönder
     4.Adim Validation yap
        */

    @Test
    public void test01(){

        //EndPoint i oluşturalım
        String endPoint="https://restful-booker.herokuapp.com/booking/10";

        //2. adım Request gönder
        Response response=given().
                accept("application/json").
                when().
                get(endPoint);
        response.prettyPrint();

        response.then().assertThat().
                contentType("application/json").
                statusCode(200).
                body("firstname",equalTo("Mark"),
                        "lastname",equalTo("Ericsson"),
                        "totalprice",equalTo(483));

        //Body nin validationu
        response.then().assertThat().body("firstname",equalTo("Eric"),
                "lastname",equalTo("Brown"),
                "totalprice",equalTo(562),
                "depositpaid",equalTo(false),
                "bookingdates.checkin",equalTo("2015-09-12"),
                "bookingdates.checkin",equalTo("2019-04-26"));

        //JsonPath ile oluşturalım obje ureterek hızlandıralım
            JsonPath json= response.jsonPath();
        //validationu Assert uzerinden yapmalıyız json da
        Assert.assertEquals("Susan",json.getString("firstname"));
        Assert.assertEquals("Wilson",json.getString("lastname"));
        Assert.assertEquals(945,json.getInt("totalprice"));
        Assert.assertEquals(true,json.getBoolean("depositpaid"));
        Assert.assertEquals("2015-07-30",json.getString("bookingdates.checkin"));
        Assert.assertEquals("2020-03-02",json.getString("bookingdates.checkout"));
    }
}
