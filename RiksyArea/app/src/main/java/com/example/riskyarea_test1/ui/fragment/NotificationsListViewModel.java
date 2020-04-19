package com.example.riskyarea_test1.ui.fragment;

import com.example.riskyarea_test1.data.controller.AnnouncementController;
import com.example.riskyarea_test1.data.model.response.SectionAnnouncement;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsListViewModel extends ViewModel {

    private LiveData<ArrayList<SectionAnnouncement>> announcementList ;
    private AnnouncementController announcementController = new AnnouncementController();

    public LiveData<ArrayList<SectionAnnouncement>> getAnnouncementList() {
        announcementList = announcementController.getAnnouncementList();
        return announcementList;
    }
}

