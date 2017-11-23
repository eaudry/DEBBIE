package com.eaudry.Views;

import com.eaudry.POJOs.Track;
import net.miginfocom.swing.MigLayout;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

/**
 * Created by Eliott on 06/07/2016.
 */
public class MapGUI extends JFrame{
    private static final Logger LOG = LoggerFactory.getLogger(MapGUI.class);
    private final JPanel frame = new JPanel();
    private final JPanel framemenu = new MapMenu();
    private final Color backgroundColor = new Color(225, 225, 225);
    //private final MigLayout layout = new MigLayout("aligny center","[]10px[]0.7cm[]","center");
    private final MigLayout layout = new MigLayout("debug, aligny center","[150px]5px[250]","center");
    private final Track track;
    private static final JXMapViewer mapViewer = new JXMapViewer();
    public static final JXMapKit jXMapKit = new JXMapKit();


    public MapGUI(Track track){
        this.track = track;
        final JXMap jxMap = new JXMap(track);
        this.setVisible(true);
        this.setTitle("AIS Track");
        this.setResizable(false);
        this.setSize(1550, 1080);
        this.setLocationRelativeTo(null);
        //Only close this window on exit:
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(frame);

        //On définit le type de layout de notre main panel
        frame.setBackground(backgroundColor);
        frame.setLayout(layout);

        //Specif tous les elements
        Font police1 = new Font("Tahoma", Font.BOLD, 16);
        Font police4 = new Font("Tahoma", Font.BOLD, 14);
        Font police2 = new Font("Arial", Font.BOLD, 15);
        Font police3 = new Font("Arial", Font.BOLD, 16);

        //map size
        jxMap.setPreferredSize(new Dimension(1350, 980));

        //map zoom enabled
        jxMap.addMouseWheelListener(new ZoomMouseWheelListenerCursor(jxMap));

        //map drag enabled
        MouseInputListener mia = new PanMouseInputListener(jxMap);
        jxMap.addMouseListener(mia);
        jxMap.addMouseMotionListener(mia);

        //Design de l'interface
        frame.add(jxMap, "span 4");
        frame.add(framemenu, "span 1");


        pack();
    }

    /*public class dqef implements MouseWheelListener{
        public void mouseWheelmoved(MouseWheelEvent e){
            LOG.info("dzqefs");
        }
    }*/

}
