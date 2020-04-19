package com.example.riskyarea_test1.data.controller;

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

public class DoctorController extends BaseController {
    private Retrofit retrofit = getBuilder().build();
    private APIConfig apiConfig = retrofit.create(APIConfig.class);
    MutableLiveData<ArrayList<Doctor>> _list = new MutableLiveData<>();

    public LiveData<ArrayList<Doctor>> getDoctorList() {
//        MutableLiveData<ArrayList<PassportListResponse>> _list = new MutableLiveData<>();
        apiConfig.getDoctorList().enqueue(new Callback<ArrayList<Doctor>>() {
            @Override
            public void onResponse(Call<ArrayList<Doctor>> call, Response<ArrayList<Doctor>> response) {
                _list.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Doctor>> call, Throwable t) {

            }
        });
        return _list;
    }
}
