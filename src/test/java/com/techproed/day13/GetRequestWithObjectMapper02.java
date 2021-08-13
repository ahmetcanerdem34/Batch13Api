package com.techproed.day13;

import com.techproed.testBase.TestBaseHerokuApp;
import com.techproed.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper02 extends TestBaseHerokuApp {

/*
https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
status kodun 200 ve response body’nin
{
   "firstname": "Mark",
   "lastname": "Wilson",
   "totalprice": 284,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-08-10",
       "checkout": "2018-06-22"
   }
}
 */
    @Test
    public void test(){
        //url olustur
        spec02.pathParams("parametre1","booking","parametre2",2);
        //expected data olustur ObjectMapper ile
        //once string yapılır
        String jsonData = "{\n" +
                "   \"firstname\": \"Susan\",\n" +
                "   \"lastname\": \"Jackson\",\n" +
                "   \"totalprice\": 580,\n" +
                "   \"depositpaid\": false,\n" +
                "   \"bookingdates\": {\n" +
                "       \"checkin\": \"2017-12-24\",\n" +
                "       \"checkout\": \"2020-06-29\"\n" +
                "   }\n" +
                "}";

        HashMap<String,Object> expectedDataMap= JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        System.out.println(expectedDataMap);
        //{firstname=Jim, bookingdates={checkin=2021-02-16, checkout=2021-05-10}, totalprice=544, depositpaid=false, lastname=Ericsson}


        //request olustur
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");
        response.prettyPrint();
        /*
        {
    "firstname": "Jim",
    "lastname": "Ericsson",
    "totalprice": 544,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2021-02-16",
        "checkout": "2021-05-10"
    }
}
        //actualData olustur  response u Map e cevir
         */
        HashMap<String,Object> actualData=JsonUtil.convertJsonToJava(response.asString(),HashMap.class);
        System.out.println(actualData);
        //{firstname=Jim, bookingdates={checkin=2021-02-16, checkout=2021-05-10}, totalprice=544,
        // depositpaid=false, lastname=Ericsson}

        //Assert edelim
        Assert.assertEquals(expectedDataMap.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualData.get("bookingdates")).get("checkin"));//Map içinde map var ona(checkin) ulasmak için casting
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map)actualData.get("bookingdates")).get("checkout"));//Map içinde map var(checout) ona ulasmak için casting

                //SON OLARAK GENEL AÇIKLAMA
        /*
        a) Expected Data : Request gonderidigimde boyle bir sonuc gelmeli diye olusturuyorum.
- testdata map, testdata JSONObject, pojo, object mapper seklinde 4 farkli yontemi vardir.
b) Actual Data
- testdata map, testdata JSONObject, pojo, object mapper seklinde 4 farkli yontemi vardir.
- Expected ile Actual arasindaki tek fark Actual'i response'dan aliyoruz.
- Response'den almanin yollari :
    - body matchers,
    - jsonPath,
    - De-serialization(gson, objectmapper)
         */

    }
}
