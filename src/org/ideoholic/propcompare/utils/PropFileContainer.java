package org.ideoholic.propcompare.utils;

import java.io.*;
import java.util.*;

/**
 * Created by Reehan on 01-07-2015.
 * Class to hold the configuration given in a file.
 */
public class PropFileContainer implements Constants {
    private Properties prop;

    public PropFileContainer(String propFileName) throws IOException {
        if (null == propFileName || (propFileName.trim()).isEmpty()) {
            throw new IOException("Given properties file name is null or is empty");
        }
        File f = new File(propFileName);
        if (!f.exists() || f.isDirectory()) {
            System.out.println("Given properties file is a directory or does not exist");
            throw new IOException("Given properties file is a directory or does not exist");
        }
        loadPropertiesFile(new FileReader(f));
    }

    public PropFileContainer(InputStream propFileStream) throws IOException {
        if (null == propFileStream) {
            throw new IOException("Given properties file stream is null");
        }
        loadPropertiesFile(propFileStream);
    }

    public Properties getProp() {
        return prop;
    }

    private void loadPropertiesFile(InputStream inStream) throws IOException {
        prop = new Properties();
        prop.load(inStream);
    }

    private void loadPropertiesFile(Reader inStream) throws IOException {
        prop = new Properties();
        prop.load(inStream);
    }

    public String getPropertyValue(String key) {
        String value = prop.getProperty(key);
        if (null == value) {
            return value;
        }
        return value.trim();
    }

    public boolean doesPropertyExist(String key){
        return prop.containsKey(key);
    }

    public List<String> getListOfMissingElements(PropFileContainer otherObject) {
        List<String> list = new ArrayList<String>();

        for (Object keyObj : otherObject.getProp().keySet()) {
            if (!getProp().containsKey(keyObj)) {
                list.add((String) keyObj);
            }
        }
        return list;
    }

    public List<String> getListOfMissingElements(PropFileContainer otherObject, List<String> ignoreElements) {
        List<String> list = new ArrayList<String>();

        List<String> elementsList = copyToList(otherObject.getProp().keySet());

        for (String element : elementsList) {
            if (!ignoreElements.contains(element) && !getProp().containsKey(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public List<String> getAllKeys(){
        return copyToList(prop.keySet());
    }

    public List<String> getAllKeysSorted(){
        List<String> keys = copyToList(prop.keySet());
        Object[] keysArray = keys.toArray();
        Arrays.sort(keysArray);

        keys.clear();
        for (Object keyObj : keysArray) {
            keys.add(keyObj.toString());
        }
        return keys;
    }

    private List<String> copyToList(Set<Object> objects) {
        List<String> list = new ArrayList<String>();
        for (Object keyObj : objects) {
            if (!(keyObj instanceof String)) continue;
            String listObj = (String) keyObj;
            list.add(listObj);
        }
        return list;
    }
}
