package PR3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SafeMap<K, V> implements Map<K, V> {
    private Map<K, V> map = new HashMap<>();
    @Override
    synchronized public int size() {
        return map.size();
    }

    @Override
    synchronized public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    synchronized public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    synchronized public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    synchronized public V get(Object key) {
        return map.get(key);
    }

    @Override
    synchronized public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    synchronized public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    synchronized public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    synchronized public void clear() {
        map.clear();
    }

    @Override
    synchronized public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    synchronized public Collection<V> values() {
        return map.values();
    }

    @Override
    synchronized public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
