package com.example.riskyarea_test1.data.controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class BaseController {

    public  Retrofit.Builder getBuilder(){
//        return new Retrofit.Builder().baseUrl("http://192.168.1.213:8082/").
//                addConverterFactory(GsonConverterFactory.create());
        return new Retrofit.Builder().baseUrl("https://riskyarea1.herokuapp.com/").
                addConverterFactory(GsonConverterFactory.create());

    }

}
