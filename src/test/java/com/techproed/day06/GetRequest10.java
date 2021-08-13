package com.techproed.day06;

import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends TestBaseDummy {
    /*
    *******BURADA VERİLEN DATALAR EXPECTED DATALARDIR ASSERT TE EXPECTED KISMINI OLUSTURUR**********ÖNEMLİ****
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde
Dönen response un
 Status kodunun 200,
 1)10’dan büyük tüm id’leri ekrana yazdırın ve
10’dan büyük 14 id olduğunu,
 2)30’dan küçük tüm yaşları ekrana yazdırın ve
  bu yaşların içerisinde en büyük yaşın 23 olduğunu
 3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
  bunların içerisinde “Charde Marshall” olduğunu test edin
     */

    @Test
    public void test(){
        spec03.pathParam("parametre1","employees");
        Response response=given().
                accept("application/json").
                spec(spec03).when().
                get("/{parametre1}");
        response.prettyPrint();


        JsonPath json=response.jsonPath();
        // Status kodunun 200,
        Assert.assertEquals(200, response.getStatusCode());
        //1)10’dan büyük tüm id’leri ekrana yazdırın ve

        //Groovy dili: javanın alt dillerinden biridir=>tırnak içerisinde yazılır java tanır bunu
        //it ifadesi this anlamındadır
        List<Integer> list = json.getList("data.findAll{it.id>10}.id");//id si 10 dan buyuk olan
        //belirtilen datanın içerisinde istenen
        //şarta bağlı olan değerleri getirir -->findAll
        System.out.println(list);

        //10’dan büyük 14 id olduğunu,
        Assert.assertEquals(14,list.size());
        //[11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]

        // 2)30’dan küçük tüm yaşları ekrana yazdırın ve
        //  bu yaşların içerisinde en büyük yaşın 23 olduğunu

        /*
List<Integer> age30 = listAge.stream().filter(x -> x < 30).collect(Collectors.toList());
Collections.sort(age30);
Collections.reverse(age30);
Assert.assertEquals(23, (int) age30.get(0));

  List<Integer> list=json.getList("data.findAll{it.id<30}.employee_age");

  List<Integer>list =json.getList("data.findAll{it.employee_age<30}.employee_age");
System.out.println(yasList);
         */

        List<Integer> yasList=json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yasList);
        Collections.sort(yasList);//listeyi kucukten buyuge sırlamaya yardımcı oluyor sort
        Assert.assertEquals(Integer.valueOf("23"),yasList.get(yasList.size()-1));//gir sondakini getir ve 23 e eşit mi bak

        //soft assert te kullanilabilir işlemin devam etmesi için
        //3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
        //  bunların içerisinde “Charde Marshall” olduğunu test edin
        List<String> nameList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(nameList);
        //[Cedric Kelly, Brielle Williamson, Charde Marshall, Tatyana Fitzpatrick, Paul Byrd, Yuri Berry]
        Assert.assertTrue(nameList.contains("Brielle Williamson"));//Pass

    }
}
