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

    public int getRiskPoint() {
        return sharedPreferences.getInt(SettingsValues.RISK_POINT, 0);
    }

    public void setRiskPoint(int riskPoint) {
        spEditor = sharedPreferences.edit();
        spEditor.putInt(SettingsValues.RISK_POINT, riskPoint);
        spEditor.apply();
    }
}