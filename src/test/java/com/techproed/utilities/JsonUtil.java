package com.techproed.utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    //object mapper kullanarak de_serialization yapma yöntemi
    private  static ObjectMapper mapper;
    static{
        mapper=new ObjectMapper();
    }
    //Methodumuzu oluşturacağız. Stringe çevrilmiş json datasını java objesine dönüştürecek.
    public static <T> T convertJsonToJava(String json,Class<T> cls){//json ı javaya ceviren method
        //buradaki T-->genelde developerların kullandığı generic bir ifadedir
        //Tum data typları kapsar
        //burada bize gelen Json ifadesini kendisi tarıyor ve ne yapması gerektiğine kendiis karar veriyor
        //Cevirecegi uygun java objesini kendi belirler
        T javaResult= null;
        try {//trycatch ile throw arasındaki fark trycatch i biz genellikle reusable methodlarda kullanırız
            //throw ile handle edersek burda bunu kullandığım da farklı class da surekli handle etmemi ister
            javaResult = mapper.readValue(json, cls);

            //methoda gelen parametreleri okuyor ve bunu mapper tipinde javaResult a aktarıyor
        } catch (IOException e) {
            System.err.println("json datası javaya dönüştürülemedi"+e.getMessage());
        }
        return javaResult;
    }
}
