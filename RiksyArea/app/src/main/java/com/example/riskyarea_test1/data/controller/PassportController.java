package com.example.riskyarea_test1.data.controller;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.riskyarea_test1.data.model.response.Doctor;
import com.example.riskyarea_test1.data.model.response.Passport;
import com.example.riskyarea_test1.utils.APIConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PassportController extends BaseController {
    private Retrofit retrofit = getBuilder().build();
    private APIConfig apiConfig = retrofit.create(APIConfig.class);
    MutableLiveData<ArrayList<Passport>> _list = new MutableLiveData<>();

    public LiveData<ArrayList<Passport>> getPassportList() {
//        MutableLiveData<ArrayList<PassportListResponse>> _list = new MutableLiveData<>();
        apiConfig.getPassportList().enqueue(new Callback<ArrayList<Passport>>() {
            @Override
            public void onResponse(Call<ArrayList<Passport>> call, Response<ArrayList<Passport>> response) {
                _list.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Passport>> call, Throwable t) {

            }
        });

        return _list;
    }
}
