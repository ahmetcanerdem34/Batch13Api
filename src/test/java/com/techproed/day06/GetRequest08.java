package com.techproed.day06;

import com.techproed.testBase.TestBaseDummy;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends TestBaseDummy {

    /*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
   1) Butun calisanlarin isimlerini consola yazdıralim
   2) 3. calisan kisinin ismini konsola yazdıralim
   3) Ilk 5 calisanin adini konsola yazdiralim
   4) En son calisanin adini konsola yazdiralim
     */

    @Test
    public void test01(){
        //  http://dummy.restapiexample.com/api/v1/employees  -->bu url e git butun çalışanları getir
        spec03.pathParams("parametre1","employees");

        //şimdi isteğmizi gönderelim
        Response response=given().accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");
        //sonuc geliyor mu bakalım
        response.prettyPrint();//butun işçi (24 kişi)listesini listeledi

        //ContentType.JSON  ile application/json arasındaki farkı nedir---->AYNI JSON daha dinamik

        //butn işçileri yani dataları getriebilir miyiz

        //1) Butun calisanlarin isimlerini consola yazdıralim
        //response.jsonPath(); methodu JsonPath class’indan obje ureterek response
        //uzerinden JsonPath class’indaki methodlari kullanmamizi saglar
        JsonPath json=response.jsonPath();
        System.out.println(json.getString("data.employee_name"));//isimler string olduğu için getString diyoruz
       //data.employy_name diyoruz çünkü employy data nın child ı
        //şimdi getList olarak çağıralım
        System.out.println(json.getList("data.employee_name"));//getString leaynı şekilde listeyi verdi

       //2) 3. calisan kisinin ismini konsola yazdıralim
        System.out.println(json.getString("data.employee_name[2]"));//Ashton Cox

        //3) Ilk 5 calisanin adini konsola yazdiralim
        System.out.println(json.getString("data.employee_name[0,1,2,3,4]"));//[Tiger Nixon, Garrett Winters, Ashton Cox, Cedric Kelly, Airi Satou]


        //4) En son calisanin adini konsola yazdiralim
        System.out.println(json.getString("data.employee_name[-1]"));//   Doris Wilder
    }
}
