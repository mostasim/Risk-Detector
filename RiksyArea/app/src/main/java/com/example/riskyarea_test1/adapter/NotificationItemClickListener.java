package com.example.riskyarea_test1.adapter;


import com.example.riskyarea_test1.data.model.response.Announcement;

@FunctionalInterface
public interface NotificationItemClickListener {
    void onClick(Announcement announcement);
}
