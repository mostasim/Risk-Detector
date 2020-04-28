package com.example.riskyarea_test1.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.riskyarea_test1.data.model.SettingsValues;

public class PreferenceUtil {

    public SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;

    public PreferenceUtil(Context mContext) {
        super();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public String getSubmittedDate() {
        return sharedPreferences.getString(SettingsValues.SUBMITTED_DATE, "");
    }

    public void setSubmittedDate() {
        spEditor = sharedPreferences.edit();
        spEditor.putString(SettingsValues.SUBMITTED_DATE, SettingsValues.currentDate());
        spEditor.apply();
    }
}