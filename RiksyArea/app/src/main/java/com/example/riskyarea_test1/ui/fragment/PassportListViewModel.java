package com.example.riskyarea_test1.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.riskyarea_test1.data.controller.PassportController;
import com.example.riskyarea_test1.data.model.response.Doctor;
import com.example.riskyarea_test1.data.model.response.Passport;

import java.util.ArrayList;

public class PassportListViewModel extends ViewModel {

    private LiveData<ArrayList<Passport>> passportList ;
    private PassportController passportController = new PassportController();

    public LiveData<ArrayList<Passport>> getPassportList() {
        passportList = passportController.getPassportList();
        return passportList;
    }
}

