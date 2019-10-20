package com.example.riskyarea_test1.data.model;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class SettingsValues {

    public static boolean ring = false;
    public static String refresh="5";
    public static String radius="50";

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

}
