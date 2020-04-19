package com.example.riskyarea_test1.adapter;


import com.example.riskyarea_test1.data.model.response.Doctor;

@FunctionalInterface
public interface DoctorListItemClickListener {
    void onClick(Doctor doctor);
}
