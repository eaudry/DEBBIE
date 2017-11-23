package com.eaudry.POJOs;

import org.jxmapviewer.viewer.GeoPosition;

import java.util.Date;
import java.util.List;

/**
 * Created by Eliott on 08/07/2016.
 */
public class AISPoint {

    private final GeoPosition geoposition;
    private Date timestamp = null;

    public AISPoint(GeoPosition geoposition, Date timestamp){
        this.geoposition = geoposition;
        this.timestamp = timestamp;
    }

    public AISPoint(GeoPosition geoposition){
        this.geoposition = geoposition;
    }

    public GeoPosition getGeoposition(){
        return this.geoposition;
    }

}
