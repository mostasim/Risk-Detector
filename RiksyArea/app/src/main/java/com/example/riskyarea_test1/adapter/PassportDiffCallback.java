package com.example.riskyarea_test1.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.riskyarea_test1.data.model.response.Doctor;
import com.example.riskyarea_test1.data.model.response.Passport;


public class PassportDiffCallback extends DiffUtil.ItemCallback<Passport> {

    @Override
    public boolean areItemsTheSame(@NonNull Passport oldItem, @NonNull Passport newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Passport oldItem, @NonNull Passport newItem) {
        return oldItem.equals(newItem);
    }
}
