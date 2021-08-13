package com.techproed.day06;

import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends TestBaseDummy {

    /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde,
status kodun 200,
gelen body de,
5. çalışanın isminin "Airi Satou" olduğunu ,
6. çalışanın maaşının "372000" olduğunu ,
Toplam 24 tane çalışan olduğunu,
"Rhona Davidson" ın employee lerden biri olduğunu
"21", "23", "61" yaşlarında employeeler olduğunu test edin
     */

    @Test
    public void test01(){

        spec03.pathParam("parametre1","employees");

        Response response=given().accept("application/json").spec(spec03).when().get("/{parametre1}");
        response.prettyPrint();

        //status kodun 200, olduğunu doğrulayın
        Assert.assertEquals(200,response.getStatusCode());

        JsonPath json=response.jsonPath();//responstan gelen cevabı json e ata, obje oluşturarak

        //5. çalışanın isminin "Airi Satou" olduğunu ,
        //response u json a atadığımız için respons.getString yerine json.getString yazıyoruz
        Assert.assertEquals("Airi Satou",json.getString("data.employee_name[4]"));

        //6. çalışanın maaşının "372000" olduğunu ,
        Assert.assertEquals(372000, json.getInt("data.employee_salary[5]"));

        //Toplam 24 tane çalışan olduğunu,
        //data.id, employee_name ve diğer seçeneklerdende bulabiliriz
        Assert.assertEquals(24,json.getList("data.id").size());

        //"Rhona Davidson" ın employee lerden biri olduğunu
        Assert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));

        //"21", "23", "61" yaşlarında employeeler olduğunu test edin
        Assert.assertTrue(json.getList("data.employee_age").contains(21));
        Assert.assertTrue(json.getList("data.employee_age").contains(23));
        Assert.assertTrue(json.getList("data.employee_age").contains(61));
        //ustteki kullanım dinamik olmaz liste daha geniş olsa bu şekilde yapılmaz

        //öncelikle expextedList oluşturmalıyız
        List<Integer> yasList= new ArrayList<Integer>();//as list te diyebilirdik anacak; liste gib davranır ama her bilgiyi getrimeyebilir
        yasList.add(21);
        yasList.add(23);
        yasList.add(61);
        //bu şekilde de sırayla girmek zorundayız olmadı
        System.out.println(yasList);//[21, 23, 61]
        //burdaki değerler data.employee_age de varmı onu dorulamamuz gerkir
        //containsAll ile bir listenin başka bir liste içinde olup olmadığına bakabiliyoruz
        Assert.assertTrue(json.getList("data.employee_age").containsAll(yasList));
    }
}
