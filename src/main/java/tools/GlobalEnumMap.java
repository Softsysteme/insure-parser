package tools;

import java.util.HashMap;

import insure.SimpleEnum;

public class GlobalEnumMap {

    private HashMap<String, SimpleEnum> cache;
    private static GlobalEnumMap globalSpace = null;

    GlobalEnumMap() {
        cache = new HashMap<String, SimpleEnum>();
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

    public synchronized void store(String id, SimpleEnum value) {
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

}
