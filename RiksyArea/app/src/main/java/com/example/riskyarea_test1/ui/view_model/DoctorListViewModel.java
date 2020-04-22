package com.example.riskyarea_test1.ui.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.riskyarea_test1.data.controller.DoctorController;
import com.example.riskyarea_test1.data.dto.DoctorDto;
import com.example.riskyarea_test1.data.model.response.Doctor;

import java.util.ArrayList;

public class DoctorListViewModel extends ViewModel {

    public LiveData<ArrayList<Doctor>> doctorList = new MutableLiveData<>();
    private DoctorController doctorController = DoctorController.getController();

    public LiveData<ArrayList<Doctor>> getDoctorList() {
        return doctorController.getDoctorList();
    }

    public LiveData<Boolean> registerDoctor(DoctorDto doctorDto) {
        return doctorController.registerDoctor(doctorDto);
    }

    public void fetch() {
        doctorController.fetchDoctorList();
    }
}

