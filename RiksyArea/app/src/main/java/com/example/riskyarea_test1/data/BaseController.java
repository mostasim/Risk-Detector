package com.example.riskyarea_test1.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseController {
    public Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.1.213:8087/").
            addConverterFactory(GsonConverterFactory.create());


}
