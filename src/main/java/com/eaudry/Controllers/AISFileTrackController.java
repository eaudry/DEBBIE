package com.eaudry.Controllers;

import com.eaudry.POJOs.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jxmapviewer.viewer.GeoPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public Track computeJSONfile(String filepath){
        Track track = new Track();
        //String jsonFile = "/Users/mkyong/Downloads/marine-traffic.json";

        try {
            //read json file data to String
            byte[] jsonData = Files.readAllBytes(Paths.get(filepath));

            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);

            //convert json string to object
            FleetTrajectPOJO fleetTraject = objectMapper.readValue(jsonData, FleetTrajectPOJO.class);
            VesselTrajectPOJO[] vesselTrajectArray = fleetTraject.getFleet();

            /** Dla merde pour l'istant: mixe tous les vaisseaux en 1 trajet, car need pour générer la jxmap so far, à changer + tard */

            for (int i = 0; i < vesselTrajectArray.length; i++){
                VesselTrajectPOJO vesselTraject = vesselTrajectArray[i];
                PositionPOJO[] positionPOJOArray = vesselTraject.getPositions();

                for (int j = 0; j < positionPOJOArray.length; j++){
                    PositionPOJO positionPOJO = positionPOJOArray[j];
                    Double latitude = positionPOJO.getLatitude();
                    Double longitude = positionPOJO.getLongitude();

                    GeoPosition geoposition = new GeoPosition(latitude, longitude);
                    AISPoint aisPoint = new AISPoint(geoposition);
                    track.addPosition(aisPoint);

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return track;
    }

}
