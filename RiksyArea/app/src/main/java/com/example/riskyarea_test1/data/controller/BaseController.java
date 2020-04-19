package com.example.riskyarea_test1.data.controller;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
//        return new Retrofit.Builder().baseUrl("https://riskyarea1.herokuapp.com/").
//                addConverterFactory(GsonConverterFactory.create());
        return new Retrofit.Builder().client(createHttpClient()).baseUrl("http://206.189.197.151:8888/api/").
                addConverterFactory(GsonConverterFactory.create());
    }

    private OkHttpClient createHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);

        return builder.build();
    }

}
