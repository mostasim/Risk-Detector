package com.example.riskyarea_test1.helper;

public enum MarkedPlaceType {
    INFECTED("INFECTED"),
    LOCAL_GATHERING("LOCAL_GATHERING"),
    COMMUNITY_TRANSMISSION("COMMUNITY_TRANSMISSION"),
    ;

    public final String label;

    private MarkedPlaceType(String label) {
        this.label = label;
    }
}
