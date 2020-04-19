package com.example.riskyarea_test1.ui.fragment;

import com.example.riskyarea_test1.data.controller.DoctorController;
import com.example.riskyarea_test1.data.dto.DoctorDto;
import com.example.riskyarea_test1.data.model.response.Doctor;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DoctorListViewModel extends ViewModel {

    private LiveData<ArrayList<Doctor>> doctorList ;
    private DoctorController doctorController = new DoctorController();

    public LiveData<ArrayList<Doctor>> getDoctorList() {
        doctorList = doctorController.getDoctorList();
        return doctorList;
    }
    public LiveData<Boolean> registerDoctor(DoctorDto doctorDto) {
        return doctorController.registerDoctor(doctorDto);
    }

}

