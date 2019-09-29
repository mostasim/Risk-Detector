package com.example.riskyarea_test1.data;

import com.example.riskyarea_test1.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIConfig {

    @GET("rest/user/getAll")
    public Call<List<User>> getAllUser();
}
