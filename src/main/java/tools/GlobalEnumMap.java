package tools;

import java.util.HashMap;

import insure.core.IEnumeration;

public class GlobalEnumMap {

    private static HashMap<String, IEnumeration> cache;
    private static GlobalEnumMap globalSpace = null;

    GlobalEnumMap() {
        cache = new HashMap<String, IEnumeration>();
    }

    public static synchronized GlobalEnumMap getInstance() {
        if (globalSpace == null) {
            globalSpace = new GlobalEnumMap();
        }

        return globalSpace;
    }

    public synchronized void clear() {
        cache.clear();
    }

    public synchronized static void store(String id, IEnumeration value) {
        cache.put(id, value);
    }

    public synchronized Object retrieve(String id) {
        return cache.get(id);
    }

    public synchronized Object remove(String id) {
        return cache.remove(id);
    }

    public synchronized boolean containsKey(String id) {
        return cache.containsKey(id);
    }

    public synchronized static boolean mapEmpty() {
        return cache.isEmpty();
    }

}
