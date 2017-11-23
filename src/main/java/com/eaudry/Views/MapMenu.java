package com.eaudry.Views;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Eliott on 08/07/2016.
 */
public class MapMenu extends JPanel{

    private final MigLayout layout = new MigLayout("debug, aligny center","","center");
    private final Color backgroundColor = new Color(225, 225, 225);
    private final JButton testButton = new JButton("TEST");

    public MapMenu(){
        this.setVisible(true);
        this.setBackground(backgroundColor);
        this.setLayout(layout);

        //Specif tous les elements
        Font police1 = new Font("Tahoma", Font.BOLD, 16);
        testButton.setFont(police1);
        //testButton.setPreferredSize(new Dimension(230,20));

        //Design de l'interface
        this.add(testButton, "span, wrap");

    }

}
