package com.eaudry.Views;

import com.eaudry.Controllers.AISFileTrackController;
import com.eaudry.POJOs.Track;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Eliott on 06/07/2016.
 */
public class MainGUI extends JFrame{
    private static final Logger LOG = LoggerFactory.getLogger(MainGUI.class);
    private final JPanel frame = new JPanel();
    /*private final JButton buttonAnalyze = new JButton("Analyze");
    private final JButton buttonStop = new JButton("Stop");
    private final JButton buttonExit = new JButton("Exit");
    private final JButton buttonOutputDirectory = new JButton("Chose output directory :");
    private final JTextArea pathTextField = new JTextArea("");
    private final JPanel spacePanel1 = new JPanel();
    private final JPanel spacePanel2 = new JPanel();
    private final JLabel analysisLabel = new JLabel("Analysis to perform :");
    private final JLabel serverLabel = new JLabel("Server name : ");
    private final JTextField serverTextField = new JTextField("analytics.searchxpr.com");
    private final JLabel dbLabel = new JLabel("Database name : ");
    private final JTextField dbTextField = new JTextField("bol201605");
    private final JLabel clientLabel = new JLabel("Client name : ");
    private final JTextField clientTextField = new JTextField("boutiqueol");
    private final JTextArea logsTextArea = new JTextArea("");
    private final JCheckBox checkBoxCompleteAnalysis = new JCheckBox("Complete Analysis");
    private final JCheckBox checkBoxPurchases = new JCheckBox("DoNotUse");
    private final JCheckBox checkBoxPurchasedRecos = new JCheckBox("DoNotUse");
    private final JCheckBox checkBoxEvents = new JCheckBox("DoNotUse");
    private final JProgressBar progressBar = new JProgressBar();*/
    private final Color backgroundColor = new Color(225, 225, 225);
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private Future future;
    //private final MigLayout layout = new MigLayout("aligny center","[]10px[]0.7cm[]","center");
    private final MigLayout layout = new MigLayout("aligny center","","center");

    private final JMenuBar menuBar  = new JMenuBar();
    private final JMenu menu  = new JMenu("Menu");;
    private final JMenuItem menuItem = new JMenuItem("Open AIS File");
    private final JTextField temporaryText = new JTextField("");

    public MainGUI(){
        this.setVisible(true);
        this.setTitle("AIS Track Analyzer");
        this.setResizable(false);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(frame);

        //On définit le type de layout de notre main panel
        frame.setBackground(backgroundColor);
        frame.setLayout(layout);
        setJMenuBar(menuBar);

        //Specs tous les elements
        Font police1 = new Font("Tahoma", Font.BOLD, 16);
        Font police4 = new Font("Tahoma", Font.BOLD, 14);
        Font police2 = new Font("Arial", Font.BOLD, 15);
        Font police3 = new Font("Arial", Font.BOLD, 16);
        /*serverLabel.setFont(police1);
        serverLabel.setForeground(Color.BLACK);
        serverLabel.setPreferredSize(new Dimension(120, 30));
        serverTextField.setFont(police2);
        serverTextField.setPreferredSize(new Dimension(550, 30));
        serverTextField.setForeground(Color.BLACK);
        dbLabel.setFont(police1);
        dbLabel.setForeground(Color.BLACK);
        dbLabel.setPreferredSize(new Dimension(150, 30));
        dbTextField.setFont(police2);
        dbTextField.setPreferredSize(new Dimension(550, 30));
        dbTextField.setForeground(Color.BLACK);
        clientLabel.setFont(police1);
        clientLabel.setForeground(Color.BLACK);
        clientLabel.setPreferredSize(new Dimension(120, 30));
        clientTextField.setFont(police2);
        clientTextField.setPreferredSize(new Dimension(550, 30));
        clientTextField.setForeground(Color.BLACK);
        buttonOutputDirectory.setPreferredSize(new Dimension(210, 30));
        buttonOutputDirectory.setFont(police2);
        pathTextField.setPreferredSize(new Dimension(550, 30));
        pathTextField.setFont(police2);
        pathTextField.setForeground(Color.BLACK);
        pathTextField.setEditable(false);
        analysisLabel.setPreferredSize(new Dimension(120, 30));
        analysisLabel.setFont(police1);
        checkBoxCompleteAnalysis.setFont(police1);
        checkBoxCompleteAnalysis.setBackground(backgroundColor);
        checkBoxPurchases.setFont(police1);
        checkBoxPurchasedRecos.setFont(police1);
        checkBoxEvents.setFont(police1);
        logsTextArea.setFont(police3);
        logsTextArea.setPreferredSize(new Dimension(773, 220));
        logsTextArea.setForeground(Color.BLACK);
        logsTextArea.setEditable(false);
        progressBar.setPreferredSize(new Dimension(773, 25));
        progressBar.setStringPainted(true);
        progressBar.setFont(police1);
        progressBar.setValue(0);
        progressBar.setString(0 + "%");
        buttonAnalyze.setPreferredSize(new Dimension(200, 45));
        buttonAnalyze.setFont(police4);
        buttonStop.setPreferredSize(new Dimension(200, 45));
        buttonStop.setFont(police4);
        buttonExit.setPreferredSize(new Dimension(200, 45));
        buttonExit.setFont(police4);
        spacePanel1.setBackground(backgroundColor);
        spacePanel1.setPreferredSize(new Dimension(120, 30));
        spacePanel2.setBackground(backgroundColor);
        spacePanel2.setPreferredSize(new Dimension(120, 30));*/

        menuBar.setPreferredSize(new Dimension(400, 30));
        menu.setFont(police4);
        menuItem.setFont(police4);
        temporaryText.setPreferredSize(new Dimension(400, 300));
        menuItem.addActionListener(new selectFileButtonListener());



        //Design de l'interface
        /*frame.add(serverLabel, "span 3");
        frame.add(serverTextField, "span 5, wrap"); // Wrap to next row
        frame.add(dbLabel, "span 3") ;
        frame.add(dbTextField, "span 5, wrap");
        frame.add(clientLabel, "span 3") ;
        frame.add(clientTextField, "span 5, wrap");
        frame.add(buttonOutputDirectory, "span 3") ;
        frame.add(pathTextField, "span 5, wrap");
        frame.add(analysisLabel, "span 3") ;
        frame.add(checkBoxCompleteAnalysis, "span 5, wrap");
        frame.add(logsTextArea, "span 8, wrap");
        frame.add(progressBar, "span 8, wrap 22px");
        frame.add(spacePanel1, "span 1");
        frame.add(buttonAnalyze, "span 2, shrink 0");
        frame.add(buttonStop, "span 2, shrink 0");
        frame.add(buttonExit, "span 2, shrink 0");
        frame.add(spacePanel2, "span 1, wrap");*/

        menuBar.add(menu);
        menu.add(menuItem);
        frame.add(temporaryText, "span 1");


        pack();
    }




    //Classe écoutant le bouton Open
    private class selectFileButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try {
                String filePath = getfilepath();
                //new MapGUI();
                AISFileTrackController aisftc = new AISFileTrackController();
                Track track = aisftc.computeCSVfile(filePath);
                new MapGUI(track);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }



    private static String getfilepath() {
        String filePath = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setPreferredSize(new Dimension(800,600));
        fileChooser.setDialogTitle("Chose output directory");
        fileChooser.setApproveButtonText("Chose");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
        return filePath;
    }

}//end Class
