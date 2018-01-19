package caches;

import insure.core.cache.ICacheConfigurationWithFactory;
import insure.core.cache.ICacheFactory;

public class SimpleEnumCacheConfig implements ICacheConfigurationWithFactory {

    public static final String NAME = "insure-parser.caches.simple-enum-cache";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public long getMaxEntries() {
        return 4711;
    }

    @Override
    public long getTimeToLive() {
        return 42;
    }

    @Override
    public boolean isOverridable() {
        return false;
    }

    @Override
    public ICacheFactory getCustomCacheFactory() {
        // TODO Auto-generated method stub
        return new SimpleEnumCacheFactory();

    }

}
