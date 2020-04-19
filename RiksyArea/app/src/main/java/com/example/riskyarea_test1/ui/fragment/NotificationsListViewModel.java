package com.example.riskyarea_test1.ui.fragment;

import com.example.riskyarea_test1.data.controller.AnnouncementController;
import com.example.riskyarea_test1.data.model.response.Announcement;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsListViewModel extends ViewModel {

    private LiveData<ArrayList<Announcement>> announcementList ;
    private AnnouncementController announcementController = new AnnouncementController();

    public LiveData<ArrayList<Announcement>> getAnnouncementList() {
        announcementList = announcementController.getAnnouncementList();
        return announcementList;
    }
}

