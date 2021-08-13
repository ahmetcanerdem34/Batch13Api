package com.techproed.day12;

import com.techproed.pojos.TodosPojo;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestWithPojo01 extends TestBaseJsonPlaceHolder {

    //pojo neden olusturulut kalıp için
    //git kalıbı TodosPojo clasında olustur

    //POJO Plain Old Javaa : Basit Eski Java Objesi
    //Pojo eccapsulation yontemi ile olusturulur

    //basit manada olusturacağımız expectedData, reguestBody ve actualDatalar için birer kalıptır
    //Class bir kalıptır ve o kalıtan objeler ureterek o clastaki metod ve degıskenleri kullanabiliyorduk


    //peki Pojo classlar nasıl olusturulur
    //1-tum json onjekten gelen tum key leri private bir değişken olarak atıyorum
    //2- her degisken için getter ve setter methodları ulatıryordum
    //3- parametresiz konstruc tır olustur
    //4- parametreli constructır olustur
    //5-toString methodu buradaki değerleri okumak için  ustteki maddelerin olduğu yer

    /*
    https://jsonplaceholder.typicode.com/todos
    Request body  {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
   Status code is 201
    response body {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
     */


        //Pojo larda adım adım gitmeliyiz bir adım olmazsa hata verir

    @Test
        public void test(){

        //url endpoint olusturduk
        spec01.pathParam("parametre1", "todos");

        //request body olustur
        TodosPojo todos=new TodosPojo(21,201,"Tidy your room",false);
        System.out.println(todos);//obje urettik TodosPojo() constraktırından
        //pojo kalıbına attığımız nesneyide yazdıralım
        //Request gonderelim

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).auth().basic("admin","password123").
                body(todos).when().post("/{parametre1}");
        response.prettyPrint();

        //De-Serialization(Gson)--->pojolar ile kullanalım
        //Todos kalıbımıza gidelim
        TodosPojo actualData=response.as(TodosPojo.class);//responsu kalıp(objeye) olan actualDataya dök

        //assert edelim
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(todos.getId(),actualData.getId() );
        Assert.assertEquals(todos.getUserId(),actualData.getUserId());
        Assert.assertEquals(todos.getTitle(),actualData.getTitle());
        Assert.assertEquals(todos.isCompleted(),actualData.isCompleted());


        //JsonPath ---POJO olusturalım
        JsonPath json=response.jsonPath();
        Assert.assertEquals(todos.getId(),json.getInt("id"));
        Assert.assertEquals(todos.getUserId(),json.getInt("userId"));
        Assert.assertEquals(todos.getTitle(),json.getString("title"));
        Assert.assertEquals(todos.isCompleted(),json.getBoolean("completed"));

        //macherclass yap
        response.then().assertThat().body("userId", equalTo(todos.getId()),
                "id",equalTo(todos.getId()),
                "title",equalTo(todos.getTitle()),
                "completed",equalTo(todos.isCompleted()));

    }
    }
