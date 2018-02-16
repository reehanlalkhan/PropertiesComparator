package org.ideoholic.propcompare.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Reehan on 02-07-2015.
 */
public class PrettyPrintingMap<K, V> {
    private Map<K, V> map;

    public PrettyPrintingMap(Map<K, V> map) {
        this.map = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<K, V> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            if (iter.hasNext()) {
                sb.append(',').append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();

    }
}
