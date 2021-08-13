package com.techproed.day10;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ahmet Aksoy",
           "salary":"1000",
           "age":"18",
           "profile_image": ""
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
{
   "status": "success",
           "data": {
        “id”:…
   },
   "message": "Successfully! Record has been added."
}
olduğunu test edin
     */

    @Test
    public void test(){

        //http://dummy.restapiexample.com/api/v1/create -->v1 sonrası parametre
        spec03.pathParam("parametre1","create");//burası End point

        //expected datadan once post yaparken request body gonderdiğim için onu olusturmalıyım
        TestDataDummy testData=new TestDataDummy();
        HashMap<String,Object> requestBodyMap=testData.setUpTestData3();
        System.out.println(requestBodyMap);
        //{profile_image=, name=Ahmet Aksoy, salary=1000, age=18}


        //expected datayı olusturalım
        HashMap<String,Object> expectedDataMap=testData.setUpTestData04();
        System.out.println(expectedDataMap);

        //request olustur---artık get yerine post olacak
        Response response=given().accept("application/json").
                spec(spec03).body(requestBodyMap).
                auth().
                basic("admin","password123").when().post("/{parametre1}");
        response.prettyPrint();

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));
    }
}
