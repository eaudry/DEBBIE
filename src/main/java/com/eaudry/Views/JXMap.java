package com.eaudry.Views;

import com.eaudry.POJOs.Track;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * Created by Eliott on 06/07/2016.
 */
public class JXMap extends JXMapViewer {


    public JXMap(Track track){

        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        tileFactory.setThreadPoolSize(8);
        this.setTileFactory(tileFactory);

        List<GeoPosition> listGeopositions = track.getTrackGeopositions();
        TrackPainter trackPainter = new TrackPainter(listGeopositions);

        // Set the focus
        //this.zoomToBestFit(new HashSet<GeoPosition>(listGeopositions), 0.7);
        this.calculateZoomFrom(new HashSet<>(listGeopositions));
        //this.optimizeZoomFrom(new HashSet<GeoPosition>(listGeopositions));
        //this.optimizeZoomFrom(new HashSet<GeoPosition>(listGeopositions));

        Set<Waypoint> waypoints = new HashSet<Waypoint>();
        for (GeoPosition geoposition : listGeopositions){
            waypoints.add(new DefaultWaypoint(geoposition));
        }

        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);

        // Create a compound painter that uses both the track-painter and the waypoint-painter
        /*List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(trackPainter);
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        this.setOverlayPainter(painter);*/
        this.setOverlayPainter(trackPainter);

    }


    public void optimizeZoomFrom(Set<GeoPosition> positions) {
        if(positions.size() >= 2) {
            int zoom = this.getZoom();
            Rectangle2D rect = this.generateBoundingRectangle(positions, zoom);

            for(int count = 0; !this.getViewportBounds().contains(rect); rect = this.generateBoundingRectangle(positions, zoom)) {
                Point2D.Double center = new Point2D.Double(rect.getX() + rect.getWidth() / 2.0D, rect.getY() + rect.getHeight() / 2.0D);
                GeoPosition px = this.getTileFactory().pixelToGeo(center, zoom);
                this.setCenterPosition(px);
                ++count;
                if(count > 30 || this.getViewportBounds().contains(rect)) {
                    break;
                }

                ++zoom;
                if(zoom > 15) {
                    break;
                }

                this.setZoom(zoom*3/5);
            }

        }
    }//fin optimizeZoom

    private Rectangle2D generateBoundingRectangle(Set<GeoPosition> positions, int zoom) {
        Point2D point1 = this.getTileFactory().geoToPixel((GeoPosition)positions.iterator().next(), zoom);
        java.awt.geom.Rectangle2D.Double rect = new java.awt.geom.Rectangle2D.Double(point1.getX(), point1.getY(), 0.0D, 0.0D);
        Iterator positionsIterator = positions.iterator();

        while(positionsIterator.hasNext()) {
            GeoPosition pos = (GeoPosition)positionsIterator.next();
            Point2D point = this.getTileFactory().geoToPixel(pos, zoom);
            rect.add(point);
        }

        return rect;
    }//fin generateBoundingRectangle

}
