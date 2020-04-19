package com.example.riskyarea_test1.adapter;


import com.example.riskyarea_test1.data.model.response.Passport;

@FunctionalInterface
public interface PassportListItemClickListener {
    void onClick(Passport passport);
}
