package com.example.riskyarea_test1.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.riskyarea_test1.data.controller.MarkedPlaceController;
import com.example.riskyarea_test1.data.model.response.MarkedPlace;

import java.util.ArrayList;

public class MapViewModel extends ViewModel {
    private LiveData<ArrayList<MarkedPlace>> _markedAreaList;
    private MarkedPlaceController markedPlaceController = new MarkedPlaceController();


    public LiveData<ArrayList<MarkedPlace>> getMarkedAreaList() {
        _markedAreaList = markedPlaceController.getMarkedPlaceList();
        return _markedAreaList;
    }
}
