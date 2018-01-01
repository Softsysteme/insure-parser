package tools;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.xml.sax.SAXException;

import com.sun.xml.internal.bind.IDResolver;

public final class IDResolverExtension extends IDResolver {
    public static final class CallableImplementation implements Callable<Object> {
        private final Object value;

        private CallableImplementation(final Object value) {
            this.value = value;
        }

        @Override
        public Object call() {
            return value;
        }
    }

    private final Map<KeyAndClass, Object> m = new HashMap<KeyAndClass, Object>();

    @SuppressWarnings("rawtypes")
    @Override
    public synchronized CallableImplementation resolve(final String key0, final Class clazz) throws SAXException {
        assert clazz != null;
        assert key0 != null;
        final KeyAndClass key = new KeyAndClass(clazz, key0);
        final Object value = m.get(key);
        return new CallableImplementation(value);
    }

    static class KeyAndClass {
        public final Class<?> clazz;
        public final String key;

        public KeyAndClass(final Class<?> clazz, final String key) {
            this.clazz = clazz;
            this.key = key;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + clazz.hashCode();
            result = prime * result + key.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final KeyAndClass other = (KeyAndClass) obj;
            if (!clazz.equals(other.clazz)) {
                return false;
            }
            if (!key.equals(other.key)) {
                return false;
            }
            return true;
        }

    }

    @Override
    public synchronized void bind(final String key0, final Object value) throws SAXException {
        assert key0 != null;
        assert value != null;
        Class<? extends Object> clazz = value.getClass();
        assert clazz != null;
        final KeyAndClass key = new KeyAndClass(clazz, key0);
        final Object oldValue = m.put(key, value);
        if (oldValue != null) {
            final String message = MessageFormat.format("duplicated key ''{0}'' => ''{1}'' - old: ''{2}''", key, value,
                    oldValue);
            throw new AssertionError(message);
        }
    }
}
