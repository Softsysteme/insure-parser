package caches;

import static insure.core.InsureConfiguration.getConfiguration;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import insure.core.cache.ICache;
import insure.core.cache.ICacheConfiguration;
import insure.core.cache.ICacheFactory;

public class SimpleEnumCacheFactory implements ICacheFactory {

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ICache<?, ?> create(final ICacheConfiguration configuration) {
        return new SimpleEnumCache(configuration.getName(),
            new TimestampedHashMap(getMaxEntries(configuration), getTimeToLive(configuration)));
    }

    private long getMaxEntries(final ICacheConfiguration configuration) {
        long entries = configuration.getMaxEntries();
        if (configuration.isOverridable()) {
            entries = getConfiguration().getLong(configuration.getName() + ".maxEntries", entries);
        }
        return entries;
    }

    private long getTimeToLive(final ICacheConfiguration configuration) {
        long time = configuration.getTimeToLive();
        if (configuration.isOverridable()) {
            time = getConfiguration().getLong(configuration.getName() + ".timeToLive", time);
        }
        return time;
    }

    private class TimestampedHashMap<String, IEnumeration> implements Map<String, IEnumeration> {

        private long timeToLive;

        private Map<String, TimestampedValue<IEnumeration>> map;

        public TimestampedHashMap(final long maxEntries, final long timeToLive) {
            this.timeToLive = timeToLive * 1000;

            map = new LinkedHashMap<String, TimestampedValue<IEnumeration>>() {

                /**
                 * 
                 */
                private static final long serialVersionUID = 1L;

                @Override
                protected boolean removeEldestEntry(final Entry<String, TimestampedValue<IEnumeration>> eldest) {
                    return (maxEntries > 0) && (size() > maxEntries);
                }
            };
        }

        @Override
        public int size() {
            return map.size();
        }

        @Override
        public boolean isEmpty() {
            return map.isEmpty();
        }

        @Override
        public boolean containsKey(final Object key) {
            return map.containsKey(key);
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public boolean containsValue(final Object value) {
            return map.containsValue(new TimestampedValue(value));
        }

        @Override
        public IEnumeration get(final Object key) {
            TimestampedValue<IEnumeration> timestamped = map.get(key);
            if (timestamped == null) {
                return null;
            }
            if ((timeToLive > 0) && ((System.currentTimeMillis() - timestamped.getTimestamp()) > timeToLive)) {
                return null;
            }
            return timestamped.getValue();
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public IEnumeration put(final String key, final IEnumeration value) {
            TimestampedValue<IEnumeration> putted = map.put(key, new TimestampedValue(value));
            return putted.getValue();
        }

        @Override
        public IEnumeration remove(final Object key) {
            TimestampedValue<IEnumeration> removed = map.remove(key);
            return (removed == null) ? null : removed.getValue();
        }

        @Override
        public void clear() {
            map.clear();
        }

        @Override
        public Set<String> keySet() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(final Map<? extends String, ? extends IEnumeration> map) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Collection<IEnumeration> values() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<Entry<String, IEnumeration>> entrySet() {
            throw new UnsupportedOperationException();
        }

        private class TimestampedValue<T> {

            private T value;
            private long timestamp;

            private TimestampedValue(final T value) {
                this.value = value;
                timestamp = System.currentTimeMillis();
            }

            public T getValue() {
                return value;
            }

            public long getTimestamp() {
                return timestamp;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = (prime * result) + getOuterType().hashCode();
                result = (prime * result) + ((value == null) ? 0 : value.hashCode());
                return result;
            }

            @Override
            @SuppressWarnings("rawtypes")
            public boolean equals(final Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null) {
                    return false;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                TimestampedValue other = (TimestampedValue) obj;
                if (!getOuterType().equals(other.getOuterType())) {
                    return false;
                }
                if (value == null) {
                    if (other.value != null) {
                        return false;
                    }
                } else if (!value.equals(other.value)) {
                    return false;
                }
                return true;
            }

            @SuppressWarnings("rawtypes")
            private TimestampedHashMap getOuterType() {
                return TimestampedHashMap.this;
            }
        }
    }
}
