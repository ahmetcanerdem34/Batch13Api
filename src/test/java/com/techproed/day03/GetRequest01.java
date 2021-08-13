package com.techproed.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
    /*
    GetRequest01:
https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde donecek cevap(response) icin
ØHTTP status kodunun 200
ØContent Type’in Json
ØVe Status Line’in HTTP/1.1 200 OK
Oldugunu test edin.
     */

    @Test
    public void test01(){

        //driver yok ona göre Stringe atayıp gidebiliriz
        //1- url belirlenmeli

        String url="https://restful-booker.herokuapp.com/booking/3";

        //2- sonrasında expected result olustur
        //body ile oluşturulur aa bu test için gerek yok


        //3- request gonder
        //request gönderilirken Response interfacinde değer oluşturlaım
        //burada body den gelen response assert etmemiz istenmeiştir, bu sebeple bu adıma gerek yok
        //given url i göndermeden önce yapılması gerekli gereklilikleri oluşturur given
        //gereklilikler neledir given onu verir, format
        //given dan sonra accept content type yapıyoruz, json formatında olmalı
        //when kullanıcı actionlarını belirtir
        Response response=given().
                accept("application/json").
                when().
                get(url);
        //bunu nasıl yazdırırım, bu url den geleni pretty ile yazdırıyoruz
        //prettty yalnızca response yi yazdırır
        response.prettyPrint();//istediğimiz daatyı yazdırır
        //pretty kapatırsak test boş olur passed der ama bişey çıkmaz
        //yukarıda yapılan işlemlerin hepsi resortte atar


        //4- response al(actual result) ve dogrula
        //burası body kısmı
        //body testi yapmadığım için actual result almıyoruz

        //5- Assert ile doğrulama işlemi yap
        //assert et neyi, bir statusun 200 oldugunu, content type nen JSON olduğunu ve status un yukardaki soruda olduğu
        response.then().assertThat().statusCode(200).
                contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");
        //bu kod ekrana bişey yazdırmaz, doğruluğunu kontrol eder paased der olduysa

        //bunu ekrana yazdırabiliriz nasıl get methodu ile
        System.out.println("status code:"+response.getStatusCode());//status koddan getirir
        System.out.println("status line:"+response.getStatusLine());//line ı kullandık onu getirir
        System.out.println("butun head leri getir:"+response.getHeaders());//butun headers ı getirir
        System.out.println(response.getContentType());

        /*
        status code:200
HTTP/1.1 200 OK
Server=Cowboy
Connection=keep-alive
X-Powered-By=Express
Content-Type=application/json; charset=utf-8
Content-Length=170
Etag=W/"aa-rjtQgPZa9VDlGPkVpKt+gZ0tyFQ"
Date=Tue, 29 Jun 2021 20:11:10 GMT
Via=1.1 vegur
application/json; charset=utf-8
         */

    }
}
