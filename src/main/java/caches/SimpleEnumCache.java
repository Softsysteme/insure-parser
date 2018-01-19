package caches;

import java.util.Map;

import insure.core.cache.ICache;

@SuppressWarnings("hiding")
public class SimpleEnumCache<String, IEnumeration> implements ICache<String, IEnumeration> {

    private java.lang.String name;
    private Map<String, IEnumeration> map;

    protected SimpleEnumCache(final String name, final Map<String, IEnumeration> map) {
        this.name = (java.lang.String) name;
        this.map = map;
    }

    @Override
    public java.lang.String getName() {
        return name;
    }

    @Override
    public IEnumeration get(final String key) {
        return map.get(key);
    }

    @Override
    public void put(final String key, final IEnumeration value) {
        map.put(key, value);
    }

    @Override
    public void remove(final String key) {
        map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }
}
