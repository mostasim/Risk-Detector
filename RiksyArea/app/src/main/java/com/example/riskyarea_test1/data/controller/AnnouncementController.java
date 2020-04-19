package com.example.riskyarea_test1.data.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.riskyarea_test1.data.model.response.Announcement;
import com.example.riskyarea_test1.data.model.response.MarkedPlace;
import com.example.riskyarea_test1.utils.APIConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnnouncementController extends BaseController {
    private Retrofit retrofit = getBuilder().build();
    private APIConfig apiConfig = retrofit.create(APIConfig.class);
    MutableLiveData<ArrayList<Announcement>> _list = new MutableLiveData<>();

    public LiveData<ArrayList<Announcement>> getAnnouncementList() {
//        MutableLiveData<ArrayList<PassportListResponse>> _list = new MutableLiveData<>();
        apiConfig.getAnnouncement().enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(Call<ArrayList<Announcement>> call, Response<ArrayList<Announcement>> response) {
                _list.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Announcement>> call, Throwable t) {

            }
        });
        return _list;
    }
}
