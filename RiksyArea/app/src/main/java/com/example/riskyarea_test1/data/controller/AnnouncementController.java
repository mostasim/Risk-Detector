package com.example.riskyarea_test1.data.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.riskyarea_test1.data.model.response.Announcement;
import com.example.riskyarea_test1.data.model.response.SectionAnnouncement;
import com.example.riskyarea_test1.utils.APIConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnnouncementController extends BaseController {
    private static AnnouncementController announcementController;
    MutableLiveData<ArrayList<SectionAnnouncement>> _list = new MutableLiveData<>();
    ArrayList<SectionAnnouncement> _listArray = new ArrayList<>();
    MutableLiveData<ArrayList<String>> _scrollAnnouncement = new MutableLiveData<>();
    private Retrofit retrofit = getBuilder().build();
    private APIConfig apiConfig = retrofit.create(APIConfig.class);

    private AnnouncementController() {
    }

    public static AnnouncementController getAnnouncementController() {
        if (announcementController == null) {
            announcementController = new AnnouncementController();
        }
        return announcementController;
    }

    public LiveData<ArrayList<SectionAnnouncement>> getAnnouncementList() {
//        MutableLiveData<ArrayList<PassportListResponse>> _list = new MutableLiveData<>();
        apiConfig.getAnnouncement().enqueue(new Callback<ArrayList<SectionAnnouncement>>() {
            @Override
            public void onResponse(Call<ArrayList<SectionAnnouncement>> call, Response<ArrayList<SectionAnnouncement>> response) {
                _list.postValue(response.body());
                _listArray = response.body();
                ArrayList<String> listAnnounce = new ArrayList<>();
                for (SectionAnnouncement sectionAnnouncement : _listArray) {
                    for (Announcement announcement : sectionAnnouncement.getAnnouncements()) {
                        listAnnounce.add(announcement.getDetails());
                    }
                }
                _scrollAnnouncement.postValue(listAnnounce);
            }

            @Override
            public void onFailure(Call<ArrayList<SectionAnnouncement>> call, Throwable t) {

            }
        });
        return _list;
    }

    public LiveData<ArrayList<String>> getScrollAnnouncement() {
        return _scrollAnnouncement;
    }
}
