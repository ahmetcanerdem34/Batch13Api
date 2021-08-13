package com.Z_apiPractice;

import io.restassured.response.Response;
import org.junit.Test;

public class practice02 {

    /*
         Positive Scenario:
                 When I send a GET Request to
                     http://dummy.restapiexample.com/api/v1/employees
             And Accept type  “application/JSON” olsun
             Then
                 HTTP Status Code 200 olsun
             And Response'in format "application/JSON" olsun
             And 24 employees olsun
             And "Tiger Ashton Cox" employee'lerden biri olsun
             And 59, 63, ve 23 employee yaslarindan biri olsun
*/
    @Test
    public void test01(){

        String endPoint="http://dummy.restapiexample.com/api/v1/employees";



    }
}
