package com.example.riskyarea_test1.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.riskyarea_test1.data.model.response.Announcement;


public class NotificationDiffCallback extends DiffUtil.ItemCallback<Announcement> {
    @Override
    public boolean areItemsTheSame(@NonNull Announcement oldItem, @NonNull Announcement newItem) {
        return oldItem.equals(newItem);
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Announcement oldItem, @NonNull Announcement newItem) {
        return oldItem.equals(newItem);
    }
}
