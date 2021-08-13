package com.techproed.day07;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends TestBaseJsonPlaceHolder {

    //API DA 4 DOĞRULAMA VERİFY METHOD KULLANIYRODUK MATCHER,JSON 3.SÜ YENİ BAŞLIYOR POJO İÇERSİNDE DE-Serilization

    /*
    *Serilization javata oluşturduğumuz bir nesneyş veya sınıfı saklamak yada transfer etmek istediğimiz
    * -formata dönüştürme işlemine Serilization denir.
    *
    * Bunun tam tersi duruma ise De-Serilization denir.Yani API da dönen responsu Map, List,List of Map, Set gibi Java
    * -objelerine çevirme işlemidir
    * -Uygun olanı Map, List ve siz seçeceksiniz
    *
    * 2 tanee kutuphanemiz var
    * 1. si gson
    * 2.si jackson
     */


    /*
https://jsonplaceholder.otypicode.com/todos/2 url ‘ine istek gönderildiğinde,
 Dönen response un
 Status kodunun 200, dönen body de,
       "completed": değerinin false
       "title”: değerinin “quis ut nam facilis et officia qui”
       "userId"  sinin 1 ve header değerlerinden
 "Via" değerinin “1.1 vegur” ve
       "Server" değerinin “cloudflare” olduğunu test edin…
     */
    @Test
    public void test01(){
        //url olustur
        spec01.pathParams("parametre1","todos","parametre2",2);

        //bana verilen TesCase deki veriler key value şeklinde olduğundan dolayı HashMap kullanabiliriz
        // Map ile expected data olustur  olusturmalıyız, gelin verilerin ilk kısmı String,
        // sonrakiler int String ve boolean olarak gelmiş, onun için Stringten sonraki kısımları
        // Object hepsini kapsadığı için Object olarak  olarak tanımlarız String,Object
        //TestCase de verilen dataları Map e yerleştşriyoruz, sıra ve isim önemli değil
        //mesela "statusCode"-->String tir, ,200-->int tir ama Object içerisine dahildir
        //mesela "completed"-->String tir, ,false-->boolean dır ama Object içerisine dahildir
        HashMap<String,Object> expectedDataMap=new HashMap<String,Object>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("completed",false);
        expectedDataMap.put("userId",1);
        expectedDataMap.put("title","quis ut nam facilis et officia qui");
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server","cloudflare");

        System.out.println("ExpectedData");
        System.out.println(expectedDataMap);
        System.out.println("---------------------");
        /*
        ExpectedData
{Server=cloudflare, completed=false, title=quis ut nam facilis et officia qui, userId=1, statusCode=200, Via=1.1 vegur}
---------------------RASTGELE SIRALARA ÇÜNKÜ MAP TE SIRALAMA YOK
         */

        //request gonder
        Response response= given().
                accept("application/json").
                spec(spec01).
                when().get("/{parametre1}/{parametre2}/");
        response.prettyPrint();


        //şimdi map olusturum  bu responsu onun içine atayarak ACTUAL data mızı oluşturmuş lacağız
        //aslında bizim actual datamız yukarsa respons ile oluşmuştu
        //extual data gibi, reponsuda HashMap e atayarak actualide map tipinde yapacağım
        //4-Actual Datayı olustur
        //json olarak gelen datayı
        //respondan gelen datayı desterilazion yap java objesine donustur
       // JsonPath json=response.jsonPath();-->böyle yapıyorduk artık hasmap e atıyoruz
        //bunu HashMap te yap, neden object, ilk gelen kısım String, sonrsında gelenler ınt boolean string
        //tasıdıgı için object koyuyoruz
        HashMap<String,Object>actualDataMap=response.as(HashMap.class);//hashMap.cals su anlama geliyor
        //response u hashMap gibi yap, yaptıgın bu hasMap(response.as(HashMap.class)) i actualDataMap e ata
        //api den gelen response body i HashMap gibi yapıyoruz
        //JsonPath json=response.jsonPath();=-->eşittir kısmındaki ile aynı response map ini jsona atadık gibi

        //sıra assert etmeye geldik
        //expected hashMap teki datayı al-->response HashMap data daki actual ile karşılatır
        //bir map teki verileri ne ile geirioruz get ile expected ve response dakileri getir ve doğrula
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());//dönen verilerde statuscode 200 ve sabit
        //body içeriisnde deği, actualDataMap body içerisindeki veirleri getirecek
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        //actual dataMap te Via ve Serverda yok onlarında status code gibirsponsunu isteyeceğiz sadece
        Assert.assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));

        System.out.println("Actual Data");
        System.out.println(actualDataMap);


    }
}
