package com.example.riskyarea_test1.ui.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.riskyarea_test1.data.controller.AnnouncementController;
import com.example.riskyarea_test1.data.model.response.SectionAnnouncement;

import java.util.ArrayList;

public class NotificationsListViewModel extends ViewModel {

    private LiveData<ArrayList<SectionAnnouncement>> announcementList;
    private AnnouncementController announcementController = AnnouncementController.getAnnouncementController();

    public LiveData<ArrayList<SectionAnnouncement>> getAnnouncementList() {
        announcementList = announcementController.getAnnouncementList();
        return announcementList;
    }
}

