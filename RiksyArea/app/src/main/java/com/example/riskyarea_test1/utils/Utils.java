package com.example.riskyarea_test1.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class Utils {
   /* @SuppressLint("MissingPermission")
    public String getIMEI(Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }*/

    public String getDeviceUniqueID(Context activity) {
        @SuppressLint("HardwareIds") String device_unique_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return device_unique_id;
    }
}
