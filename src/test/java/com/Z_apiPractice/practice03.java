package com.Z_apiPractice;

import org.junit.Test;

public class practice03 {

    /*
         Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yolladim
         https://www.gmibank.com/api/tp-customers/42697
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Ali"
     And lastname "Deckow"
     And middleInitial Leroy Hoeger Sipes
     And email com.github.javafaker.Name@7c011174@gmail.com
     And zelleEnrolled false
     And country null
        */

    @Test
    public void get(){

        //1. adım url i olustur, bu endPoint için ek olarak Bearer Token
        String endPoint="https://www.gmibank.com/api/tp-customers/42697";

        String bearerToken="";
    }
}
