package com.example.riskyarea_test1.data.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class SettingsValues {

    public static boolean ring = false;
    public static String refresh = "5";
    public static String radius = "50";

    public static String SUBMITTED_DATE = "SUBMITTED_DATE";
    public static String RISK_POINT = "RISK_POINT";

    public static void setRing(boolean ring) {
        SettingsValues.ring = ring;
    }

    public SettingsValues(String refresh, String radius) {
        this.refresh = refresh;
        this.radius = radius;
    }

    public static String getRadius() {
        if (radius.equals(null))
            return "50";
        else
            return radius;
    }

    public static String getRefresh() {
        if (refresh.equals(null))
            return "5000";
        else
            return refresh;
    }

    public static String currentDate() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c);
    }
}
