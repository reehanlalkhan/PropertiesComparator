package org.ideoholic.propcompare.service;

import org.ideoholic.propcompare.utils.Constants;
import org.ideoholic.propcompare.utils.PropFileContainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reehan on 01-07-2015.
 */
public class InputPropertiesContainer implements Constants {
    //Taking the container for accessing all the values needed for file-parsing
    private List<String> listOfFileNames = new ArrayList<String>();
    private PropFileContainer prop = null;

    public InputPropertiesContainer(String fileName) throws IOException {
        prop = new PropFileContainer(fileName);
        String fileList = prop.getPropertyValue(INPUT_FILE_LIST);

        String[] files = fileList.split(SEPARATOR);
        for (String file : files) {
            listOfFileNames.add(file.trim());
            // System.out.println("Printing the updated line:" + line);
        }
    }

    public InputPropertiesContainer(PropFileContainer propFile) throws IOException {

        String fileList = propFile.getPropertyValue(INPUT_FILE_LIST);

        String[] files = fileList.split(SEPARATOR);
        for (String file : files) {
            listOfFileNames.add(file.trim());
            // System.out.println("Printing the updated line:" + line);
        }
    }

    public List<String> getListOfFileNames() {
        return listOfFileNames;
    }


    public List<String> getIgnoreElementsList() {
        List<String> list = new ArrayList<String>();
        // Only if the property exists process it
        if (prop.doesPropertyExist(IGNORE_LIST)) {
            // Get the ignore list, split over the speparator and add them to the list
            String ignoreElements = prop.getPropertyValue(IGNORE_LIST);
            String[] elemenstList = ignoreElements.split(SEPARATOR);
            if (null != elemenstList && elemenstList.length > 0) {
                for (String element : elemenstList) {
                    list.add(element.trim());
                }
            }
        } else return null;
        return list;
    }

    private final boolean isEmpty(String input) {
        if (null == input) {
            return true;
        } else if (input.trim().isEmpty()) {
            return true;
        }
        return false;
    }

}
