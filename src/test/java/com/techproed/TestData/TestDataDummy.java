package com.techproed.TestData;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataDummy {

     /*
  http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
  {
 “id”:”11”
 "employee_name": "Jena Gaines",
"employee_salary": 90560,
"employee_age": 30,
"profile_image": "" }
} gibi olduğunu test edin.
   */


    public HashMap<String, Object> setUpTestData() {
        //expectedDataMap imizi olusturalım__Zlist tede atabiliriz ama;
        // map te key value olduğu için isimiz daha kolay
        //en dıstaki map imiz bu olacak
        HashMap<String, Object> expectedDataMap = new HashMap<String, Object>();

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        //key valu formayı yok List te atayabilirim
        List<Integer> yaslar = new ArrayList<Integer>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        /*
         {
 “id”:”11”
 "employee_name": "Jena Gaines",
"employee_salary": 90560,
"employee_age": 30,
"profile_image": "" }
         */
        //11. isci için map olusturyorum String ve Object olarak
        //key value tipi oldugu icin Map e atamalıyım
        HashMap<String, Object> onbirinci = new HashMap<String, Object>();
        onbirinci.put("id", 11);
        onbirinci.put("employee_name", "Jena Gaines");
        onbirinci.put("employee_salary", 90560);
        onbirinci.put("employee_age", 30);
        onbirinci.put("profile_image", "");


        //expectedDataMap imizi olusturalım
        expectedDataMap.put("yasListesi", yaslar);//listeki yaslar
        expectedDataMap.put("employe11", onbirinci);//listeki yaslar
        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("besinciCalisan", "Airi Satou");
        expectedDataMap.put("calisanSayisi", 24);
        expectedDataMap.put("istenenMaas", 106450);

        //return tipim expected olacak HashMap
        return expectedDataMap;

    }
/*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */

    public HashMap setUpTestData2() {//Map dondurmesi gerekiyor

        //vermis oldugu butun degerler String ve Integer onun icin HashMap imiz String Integer barındırmalı
        HashMap<String, Integer> expectedDataMap = new HashMap<String, Integer>();
        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("enyuksekMaas", 725000);
        expectedDataMap.put("ikinciYuksekMaas", 675000);
        expectedDataMap.put("enKucukYas", 19);

        return expectedDataMap;
    }
    public HashMap setUpTestData3(){

        HashMap<String,Object> requestBodyMap=new HashMap<String,Object>();
        requestBodyMap.put("name","Ahmet Aksoy");
        requestBodyMap.put("salary",1000);
        requestBodyMap.put("age",18);
        requestBodyMap.put("profile_image","");

        return requestBodyMap;
    }
    public HashMap<String,Object> setUpTestData04(){
        HashMap<String,Object> expectedDataMap= new HashMap<String,Object>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("status","success");
        expectedDataMap.put("message","Successfully! Record has been added");
    return expectedDataMap;
    }
    public JSONObject setUpDelete01(){
        /*
        {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
}
         */
        JSONObject expectedData=new JSONObject();
        expectedData.put("status","success");
        expectedData.put("data","2");
        expectedData.put("message","Successfully! Record has been deleted");
        expectedData.put("statusCode",200);
        return expectedData;
    }
}