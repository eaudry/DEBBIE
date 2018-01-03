package com.eaudry.POJOs;

/**
 * Created by eaudr on 03/01/2018.
 */
public class PositionPOJO {

    private int timestamp;
    private String source;
    private double speed;
    private double latitude;
    private double longitude;
    private double course;

    public int getTimestamp() {
        return timestamp;
    }

    public String getSource() {
        return source;
    }

    public double getSpeed() {
        return speed;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getCourse() {
        return course;
    }
}
