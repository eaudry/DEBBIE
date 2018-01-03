package com.eaudry.POJOs;


/**
 * Created by eaudr on 03/01/2018.
 */
public class VesselTrajectPOJO {

    private String name;
    private String mmsi;
    private PositionPOJO[] positions;

    public String getName() {
        return name;
    }

    public String getMmsi() {
        return mmsi;
    }

    public PositionPOJO[] getPositions() {
        return positions;
    }
}
