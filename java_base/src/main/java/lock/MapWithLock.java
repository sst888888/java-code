package lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: cp
 * @date: 2024-01-09 14:17
 */
public class MapWithLock<K, V> extends ObjWithLock<Map<K, V>>  {

    private static final long	serialVersionUID	= -652862323697152866L;
    private static final Logger log					= LoggerFactory.getLogger(MapWithLock.class);

    public MapWithLock() {
        this(new HashMap<>());
    }

    public MapWithLock(int initCapacity) {
        this(new HashMap<>(initCapacity));
    }

    /**
     */
    public MapWithLock(Map<K, V> map) {
        super(map);
    }

    /**
     * @param lock
     */
    public MapWithLock(Map<K, V> map, ReentrantReadWriteLock lock) {
        super(map, lock);
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();
        try {
            Map<K, V> map = this.getObj();
            return map.put(key, value);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
        return null;
    }

    /**
     * 如果key值已经存在，则不会把新value put进去
     * 如果key值不存在，此方法同put(key, value)
     * @param key
     * @param value
     * @return
     */
    public V putIfAbsent(K key, V value) {
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();
        try {
            Map<K, V> map = this.getObj();
            V oldValue = map.putIfAbsent(key, value);
            if (oldValue == null) {
                return value;
            } else {
                return oldValue;
            }
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
        return null;
    }

    /**
     *
     * @param otherMap
     */
    public void putAll(Map<K, V> otherMap) {
        if (otherMap == null || otherMap.isEmpty()) {
            return;
        }

        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();
        try {
            Map<K, V> map = this.getObj();
            map.putAll(otherMap);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public V remove(K key) {
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();
        try {
            Map<K, V> map = this.getObj();
            return map.remove(key);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
        return null;
    }

    /**
     * clear
     */
    public void clear() {
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();
        try {
            Map<K, V> map = this.getObj();
            map.clear();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public V get(K key) {
        ReentrantReadWriteLock.ReadLock readLock = this.readLock();
        readLock.lock();
        try {
            Map<K, V> map = this.getObj();
            return map.get(key);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            readLock.unlock();
        }
        return null;
    }

    /**
     *
     * @return
     */
    public int size() {
        ReentrantReadWriteLock.ReadLock readLock = this.readLock();
        readLock.lock();
        try {
            Map<K, V> map = this.getObj();
            return map.size();
        } finally {
            readLock.unlock();
        }
    }

    /**
     *
     * @return 如果没值，则返回null，否则返回一个新map
     */
    public Map<K, V> copy() {
        ReentrantReadWriteLock.ReadLock readLock = readLock();
        readLock.lock();
        try {
            if (this.getObj().size() > 0) {
                return new HashMap<>(getObj());
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }
}
