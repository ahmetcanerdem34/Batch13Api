package com.techproed.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBaseJsonPlaceHolder{

        //interface miz burda
       protected RequestSpecification spec01;

    @Before
        public void setup(){

            //spec i yukarde interface ile oluşturduk şimdi değer atayalım, nesne oluşturamayız
            //unun için RequestSpecBuilder() sınıfını kullanacağız burada
            //baseurl imizi burada spec01 e atıyoruz
            spec01= new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
        }


}
