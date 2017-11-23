package com.eaudry.Controllers;

import com.eaudry.POJOs.AISPoint;
import com.eaudry.POJOs.Track;
import org.jxmapviewer.viewer.GeoPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eliott on 06/07/2016.
 */
public class AISFileTrackController {

    private static final Logger LOG = LoggerFactory.getLogger(AISFileTrackController.class);

    public AISFileTrackController(){

    }

    public Track computeCSVfile(String filepath){
        Track track = new Track();
        //String csvFile = "/Users/mkyong/Downloads/GeoIPCountryWhois.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            Map<String, String> maps = new HashMap<String, String>();

            br = new BufferedReader(new FileReader(filepath));

            //skip 1st line (columns names)
            br.readLine();

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] aisRow = line.split(cvsSplitBy);


                //try get latitude and longitude, if not present : ship is in a port
                try {
                    Double latitude = Double.valueOf(aisRow[7]);
                    Double longitude = Double.valueOf(aisRow[8]);

                    /* TODO DATE ds ici et ds constructeur*/
                    //Date timestamp = aisRow[7];

                    GeoPosition geoposition = new GeoPosition(latitude, longitude);

                    AISPoint aisPoint = new AISPoint(geoposition);

                    track.addPosition(aisPoint);
                }
                catch(ArrayIndexOutOfBoundsException exception) {
                    br.readLine();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return track;
    }

}
