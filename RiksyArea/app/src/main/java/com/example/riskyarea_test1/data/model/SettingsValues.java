package com.example.riskyarea_test1.data.model;

public class SettingsValues {

    public static String refresh;
    public static String radius;

    public SettingsValues(String refresh,String radius)
    {
        this.refresh=refresh;
        this.radius=radius;
    }

    public String getRadius() {
        if (radius.equals(null))
            return "50";
        else
            return radius;
    }

    public String getRefresh() {
        if (refresh.equals(null))
            return "5000";
        else
            return refresh;
    }


}
