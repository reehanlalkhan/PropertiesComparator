package org.ideoholic.propcompare.service;

import org.ideoholic.propcompare.utils.Constants;
import org.ideoholic.propcompare.utils.PropFileContainer;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Reehan on 01-07-2015.
 */
public class PropertiesChecker implements Constants {

    Map<String, PropFileContainer> propFileContainers = new HashMap<String, PropFileContainer>();

    /**
     * Given a list of files, it converts each of the file names to PropFileContainer and stores them in its local map
     * with file name as key to the container
     *
     * @param filesList
     * @throws IOException
     */
    public PropertiesChecker(List<String> filesList) throws IOException {
        for (String fileName : filesList) {
            // System.out.println("File Name:" + fileName);
            // PropFileContainer pp = new PropFileContainer(PropFileContainer.class.getResourceAsStream(fileName));
            PropFileContainer pp = new PropFileContainer(fileName);

            File userFile = new File(fileName);
            String fName = userFile.getName() + " (Size:" + pp.getProp().size() + ")";
            propFileContainers.put(fName, pp);
        }
    }

    /**
     * Compare the properties that this checker contains and return the map of file name VS. the elements missing in
     * each of the files.
     *
     * @param ignoreElements - If null or empty list is passed then it is ignored, else the elements in the list are
     *                       to be ignored during comparison
     * @return
     */
    public Map<String, List<String>> loadPropertyFiles(List<String> ignoreElements) {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        // Get the file names
        Set<String> fileNames = propFileContainers.keySet();
        // Iterate over each of the file
        for (String str : fileNames) {
            List<String> currList;
            // This is the prop file that is being checked for missing entries
            PropFileContainer mainPropObj = propFileContainers.get(str);
            currList = mainPropObj.getAllKeys();
            if (null != ignoreElements && !ignoreElements.isEmpty()) {
                for (String element : ignoreElements) {
                    currList.remove(element);
                }
            }
            result.put(str, currList);
        }
        return result;
    }

    public Map<String, List<String>> organizeLists(Map<String, List<String>> mapList) {
        // List<String> list = new LinkedList<String>();
        Map<String, List<String>> resultMapList = new HashMap<String, List<String>>();
        Set<String> allElements = new TreeSet<>();
        for (String key : mapList.keySet()) {
            allElements.addAll(mapList.get(key));
        }

        for (String key : mapList.keySet()) {
            List<String> list = new LinkedList<String>();
            List<String> currentList = mapList.get(key);
            for (String element : allElements) {
                if (currentList.contains(element)) {
                    list.add(element);
                } else {
                    list.add(EMPTY_STRING);
                }
            }
            resultMapList.put(key, list);
        }
        return resultMapList;
    }

}
