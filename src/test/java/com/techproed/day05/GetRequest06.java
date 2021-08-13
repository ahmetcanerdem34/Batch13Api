package com.techproed.day05;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest06 extends TestBaseJsonPlaceHolder {

    /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
   accept type'i "application/json" olan GET request'i yolladigimda
   gelen response’un
  status kodunun 200
    ve content type'inin "application/json"
  ve Headers'daki "Server" in "cloudflare"
  ve response body'deki "userId"'nin 7
  ve "title" in "esse et quis iste est earum aut impedit"
  ve "completed" bolumunun false oldugunu test edin
     */


    @Test
    public void test01(){

        //DE-SERİLAZTİON =====>json dan geleni java ya cevir

        //url oluştur burda yok testBase de spec01 i çağırmalıyız
        spec01.pathParams("name","todos","id",123);//com/todos/123 kısmında 2 tane/ olduğu için 2 isim verilmeli
                            //parametreler key value şeklindedir istediğimiz şekilde iismlendirebiliriz "name" gibi
        Response response=given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{name}/{id}");//get("/name/id"); bu şekildede alalbilirdik  ama bunları swagger da ki gib süslü ler
                                        //içerisinde belirtmeliyiz ki gidip getirsin
                response.prettyPrint();

                response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                        body("userId",equalTo(7  ),
                        "title",equalTo("esse et quis iste est earum aut impedit"),
                                "completed",equalTo(false)).header("Server",equalTo("cloudflare"));
                //buraya kadar assertion kısmıı bitti

            //BU ŞEKİLDE DE YAPABİLİRİZ
        /*
        System.out.println(response.getHeader("Server"));
        Assert.assertEquals(response.getHeader("Server"),"cloudflare");
         */


    }
}
