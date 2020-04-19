package com.example.riskyarea_test1.data.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.riskyarea_test1.data.model.response.Doctor;
import com.example.riskyarea_test1.data.model.response.MarkedPlace;
import com.example.riskyarea_test1.utils.APIConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MarkedPlaceController extends BaseController {
    private Retrofit retrofit = getBuilder().build();
    private APIConfig apiConfig = retrofit.create(APIConfig.class);
    MutableLiveData<ArrayList<MarkedPlace>> _list = new MutableLiveData<>();

    public LiveData<ArrayList<MarkedPlace>> getMarkedPlaceList() {
//        MutableLiveData<ArrayList<PassportListResponse>> _list = new MutableLiveData<>();
        apiConfig.getMarkedPlace().enqueue(new Callback<ArrayList<MarkedPlace>>() {
            @Override
            public void onResponse(Call<ArrayList<MarkedPlace>> call, Response<ArrayList<MarkedPlace>> response) {
                _list.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<MarkedPlace>> call, Throwable t) {

            }
        });
        return _list;
    }
}
