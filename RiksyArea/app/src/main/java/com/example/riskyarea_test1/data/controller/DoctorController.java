package com.example.riskyarea_test1.data.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.riskyarea_test1.data.dto.DoctorDto;
import com.example.riskyarea_test1.data.model.response.Doctor;
import com.example.riskyarea_test1.utils.APIConfig;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DoctorController extends BaseController {
    MutableLiveData<ArrayList<Doctor>> _list = new MutableLiveData<>();
    MutableLiveData<Boolean> _isRegister = new MutableLiveData<>();
    private Retrofit retrofit = getBuilder().build();
    private APIConfig apiConfig = retrofit.create(APIConfig.class);

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

    public LiveData<Boolean> registerDoctor(DoctorDto doctorDto) {
        apiConfig.registerDoctor(doctorDto).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    _isRegister.postValue(true);
                }else {
                    _isRegister.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                _isRegister.postValue(false);
            }
        });
        return _isRegister;
    }

}
