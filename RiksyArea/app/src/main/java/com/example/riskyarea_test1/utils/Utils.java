package com.example.riskyarea_test1.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.provider.Settings;

public class Utils {
   /* @SuppressLint("MissingPermission")
    public String getIMEI(Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }*/

    public String getDeviceUniqueID(ContentResolver contentResolver) {
        @SuppressLint("HardwareIds") String device_unique_id = Settings.Secure.getString(contentResolver,
                Settings.Secure.ANDROID_ID);
        return device_unique_id;
    }
}
