package com.example.riskyarea_test1.adapter;

import com.example.riskyarea_test1.data.model.response.Passport;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;


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
