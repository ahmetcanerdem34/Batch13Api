package com.techproed.day13;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import com.techproed.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper01 extends TestBaseJsonPlaceHolder {

     /*
     https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
Dönen response ‘un status kodunun 200 ve body kısmının
{
   "userId": 10,
   "id": 198,
   "title": "quis eius est sint explicabo",
   "completed": true
}
Olduğunu Object Mapper kullanarak test edin
      */

    @Test
    public void test(){
        //url olusturalım
        spec01.pathParams("parametre1","todos","parametre2",198);

        //expectedData olusturalım
        //*****ilk olarak Json ifademei bir Stringe atamalıyım
        String jsonData="{\n" +
                "   \"userId\": 10,\n" +
                "   \"id\": 198,\n" +
                "   \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                "}";

        //2. olarak ObjecMapper ın cevireceği datayı nerde saklayacağım
        //biz neye cevir dersek ona ceviri Map, List
        //ma key value oldugundan dolayı Map e cevir diyelim, typımzıı belirleyelim
        //ObjectMapper yöntemi De-Serialization yapar
        HashMap<String,Object> expectedDataMap= JsonUtil.convertJsonToJava(jsonData,HashMap.class);//parametre olarak JsonData
        //converJsonToJava-->String bir parametre kabul eder,
        System.out.println(expectedDataMap);//Map olarak verecek
        //{id=198, completed=true, title=quis eius est sint explicabo, userId=10}


        //request gönderelim
        Response response=given().accept(ContentType.JSON).spec(spec01).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();
        /*
        {
    "userId": 10,
    "id": 198,
    "title": "quis eius est sint explicabo",
    "completed": true
}           BU FORMATTA GELİR BUNU MAP E CEVİRMELİYİM Kİ EXPECT İLE ACTUAL I ASSERT EDEBİLEYİM
//56-61 satrıları arası Çevrilmiş hali {userId=10, id=198, title=quis eius est sint explicabo, completed=true}
         */

        //respondan gelen datayı yani actual datayı olusturun
        Map<String,Object> actualDataMap=JsonUtil.convertJsonToJava(response.asString(),Map.class);//parametre olarak response
        //respons String olmadığı için, converJsonToJava-->String bir parametre kabul eder,
        //response String olmadığı için asString ile String yapmış oduk
        System.out.println(actualDataMap);
        //{userId=10, id=198, title=quis eius est sint explicabo, completed=true}



        //assert edlim
        Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
    }



}
