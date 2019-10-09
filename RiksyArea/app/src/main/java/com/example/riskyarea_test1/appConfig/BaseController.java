package com.example.riskyarea_test1.appConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseController {

    public  Retrofit.Builder getBuilder(){
        return new Retrofit.Builder().baseUrl("http://192.168.1.213:8082/").
                addConverterFactory(GsonConverterFactory.create());
    }

}
