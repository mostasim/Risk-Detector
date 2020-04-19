package com.example.riskyarea_test1.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.riskyarea_test1.data.model.response.Doctor;


public class DoctorDiffCallback extends DiffUtil.ItemCallback<Doctor> {
    @Override
    public boolean areItemsTheSame(@NonNull Doctor oldItem, @NonNull Doctor newItem) {
        return oldItem.equals(newItem);
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Doctor oldItem, @NonNull Doctor newItem) {
        return oldItem.equals(newItem);
    }
}
