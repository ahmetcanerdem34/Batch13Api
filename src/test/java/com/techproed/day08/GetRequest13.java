package com.techproed.day08;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends TestBaseDummy {

  /*
  http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
  {
  “id”:”11”

   */

    @Test
    public  void test(){
        spec03.pathParam("parametre1","employees");
        TestDataDummy testData=new TestDataDummy();
        HashMap<String,Object> expectedDataMap=testData.setUpTestData();
        System.out.println(expectedDataMap);
        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");
        //response.prettyPrint();
        //De-serialization işlemi
        HashMap<String,Object>actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);
        //assertion
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"),
                ((Map) ((List)actualDataMap.get("data")).get(4)).get("employee_name"));
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List) actualDataMap.get("data")).size());
        int dataSize=((List<?>) actualDataMap.get("data")).size();
        Assert.assertEquals(expectedDataMap.get("istenenMaas"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(dataSize-2)).get("employee_salary"));
        List<Integer> actualYasList=new ArrayList<Integer>();
        for(int i=0; i<dataSize; i++){
            actualYasList.add((Integer)((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        Assert.assertTrue(actualYasList.containsAll((List)expectedDataMap.get("yasListesi")));
        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("id"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("id")
        );
        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("profile_image"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image")
        );
        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("employee_name"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_name")
        );
        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("employee_salary"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary")
        );
        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("employee_age"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age")
        );

    }
}

