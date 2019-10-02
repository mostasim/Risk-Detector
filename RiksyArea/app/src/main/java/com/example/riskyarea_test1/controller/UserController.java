package com.example.riskyarea_test1.controller;

import android.util.Log;

import com.example.riskyarea_test1.Interfaces.UserListInterface;
import com.example.riskyarea_test1.Interfaces.UserLogInInterface;
import com.example.riskyarea_test1.Interfaces.UserSignUpInterface;
import com.example.riskyarea_test1.data.APIConfig;
import com.example.riskyarea_test1.data.BaseController;
import com.example.riskyarea_test1.model.UserLogIn;
import com.example.riskyarea_test1.model.UserSignUp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class UserController extends BaseController {


    Retrofit retrofit = getBuilder().build();

    UserListInterface userListInterface;



    UserSignUpInterface userSignUpInterface;
    UserLogInInterface userLogInInterface;
    APIConfig apiConfig = retrofit.create(APIConfig.class);


    public void setUserListInterface(UserListInterface userListInterface) {
        this.userListInterface = userListInterface;
    }

    public void setUserSignUpInterface(UserSignUpInterface userSignUpInterface) {
        this.userSignUpInterface = userSignUpInterface;
    }

    public void setUserLogInInterface(UserLogInInterface userLogInInterface) {
        this.userLogInInterface = userLogInInterface;
    }


    public void showUserList() {
        Call<List<UserSignUp>> callShowUserList = (Call<List<UserSignUp>>) apiConfig.getAllUser();
        callShowUserList.enqueue(new Callback<List<UserSignUp>>() {
            @Override
            public void onResponse(Call<List<UserSignUp>> call, Response<List<UserSignUp>> response) {

               if (response!=null && response.body()!=null){
                    userListInterface.success(response.body());}

            }

            @Override
            public void onFailure(Call<List<UserSignUp>> call, Throwable t) {
                userListInterface.failed(t.getMessage());
                Log.e("__TAG__", t.getMessage());
            }
        });
    }

    public void signUp(UserSignUp user)
    {

        Call<ResponseBody> callSignUp = apiConfig.signUp(user);

        callSignUp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                userSignUpInterface.success();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                userSignUpInterface.faield();
            }
        });


    }


    public void LogIn(UserLogIn userLogIn)
    {
        Call<ResponseBody> callLogIn = apiConfig.logIn(userLogIn);
        callLogIn.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                userLogInInterface.success();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                userLogInInterface.faield();
            }
        });
    }
}
