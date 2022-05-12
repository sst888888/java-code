package lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName LRUCache
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/5 20:58
 * @Version 1.0
 **/
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int maxEntries;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public LRUCache(int initialCapacity,
                    float loadFactor,
                    int maxEntries) {
        super(initialCapacity, loadFactor, true);
        this.maxEntries = maxEntries;
    }

    public LRUCache(int initialCapacity,
                    int maxEntries) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, maxEntries);
    }

    public LRUCache(int maxEntries) {
        this(DEFAULT_INITIAL_CAPACITY, maxEntries);
    }

    // not very useful constructor
    public LRUCache(Map<? extends K, ? extends V> m,
                    int maxEntries) {
        this(m.size(), maxEntries);
        putAll(m);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxEntries;
    }
}

