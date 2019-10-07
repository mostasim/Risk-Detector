package com.example.riskyarea_test1.data;

import com.example.riskyarea_test1.model.UserList;
import com.example.riskyarea_test1.model.UserLogIn;
import com.example.riskyarea_test1.model.UserSignUp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIConfig {

    @GET("rest/user/getAll")
    public Call<List<UserList>> getAllUser();

    @POST("rest/user/signUp")
    public Call<ResponseBody> signUp(@Body UserSignUp user);

    @POST("/rest/user/logIn")
    public Call<ResponseBody> logIn(@Body UserLogIn user);


}
