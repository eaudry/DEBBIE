package com.eaudry.POJOs;


import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eliott on 08/07/2016.
 */
public class Track {

    private final List<AISPoint> track = new ArrayList<>();

    public Track(){

    }

    public void addPosition(AISPoint aispoint){
        track.add(aispoint);
    }

    public List<AISPoint> getTrackAISPoints(){
        return track;
    }

    public List<GeoPosition> getTrackGeopositions(){
        List<GeoPosition> listGeopositions = new ArrayList<>();
        for (AISPoint aisPoint : track){
            listGeopositions.add(aisPoint.getGeoposition());
        }
        return listGeopositions;
    }

}
