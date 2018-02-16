package org.ideoholic.propcompare.executor;

import org.ideoholic.propcompare.service.DisplayService;
import org.ideoholic.propcompare.service.InputPropertiesContainer;
import org.ideoholic.propcompare.service.PropertiesChecker;
import org.ideoholic.propcompare.utils.Constants;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Reehan on 01-07-2015.
 */
public class MainRunnerWithUI implements Constants {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI(Map<String, List<String>> result) {
        //Create and set up the window.
        JFrame frame = new JFrame("Properties Comparator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        DisplayService newContentPane = new DisplayService(result);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        String fileName = FILELIST_FILE_NAME;
        if (args.length != 0) {
            fileName = args[0];
        }
        try {
            InputPropertiesContainer fp = new InputPropertiesContainer(fileName);
            // This call has to update the indexes and perform all the operation
            PropertiesChecker propertiesChecker = new PropertiesChecker(fp.getListOfFileNames());
            Map<String, List<String>> intermediateResult = propertiesChecker.loadPropertyFiles(fp.getIgnoreElementsList());
            final Map<String, List<String>> result = propertiesChecker.organizeLists(intermediateResult);
            intermediateResult = null; // To unset it so that it referenced object garbage collected at the earliest
            // System.out.println("Final comparing result: " + result);

            //Schedule a job for the event-dispatching thread:
            //creating and showing this application's GUI.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI(result);
                }
            });
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
