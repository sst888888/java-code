//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package lock;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MapCollectionWithLock<K, V, CC extends Collection<V>, C extends CollectionWithLock<CC, V>> extends ObjWithLock<MapWithLock<K, C>> {
    private static final long serialVersionUID = 3511183015908156445L;
    private static Logger log = LoggerFactory.getLogger(MapCollectionWithLock.class);
    private static final String CLASS_NAME = MapCollectionWithLock.class.getName();
    private MapWithLock<K, C> mapWithLock = null;

    public MapCollectionWithLock(MapWithLock<K, C> map) {
        super(map);
        this.mapWithLock = map;
    }

    public MapCollectionWithLock(MapWithLock<K, C> map, ReentrantReadWriteLock lock) {
        super(map, lock);
    }

    public void clear() {
        this.mapWithLock.clear();
    }

    public C get(K key) {
        return (C) this.mapWithLock.get(key);
    }

    public abstract C newCollection();

    public void put(K key, V value) {
        C setWithLock = this.putIfAbsent(key);
        setWithLock.add(value);
    }

    public void putAll(K key, CC values) {
        C setWithLock = this.putIfAbsent(key);
        setWithLock.addAll(values);
    }

    public C putIfAbsent(K key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            C setWithLock = (C) this.mapWithLock.get(key);

            try {
                if (setWithLock == null) {
//                    LockUtils.runWriteOrWaitRead(CLASS_NAME + key, this, () -> {
//                        if (this.mapWithLock.get(key) == null) {
//                            this.mapWithLock.put(key, this.newCollection());
//                        }
//
//                    });
                    setWithLock = (C) this.mapWithLock.get(key);
                }
            } catch (Throwable var4) {
                log.error("", var4);
            }

            return setWithLock;
        }
    }

    public void remove(K key) {
        if (key != null) {
            this.mapWithLock.remove(key);
        }
    }

    public void remove(K key, V value, CC values) {
        if (key != null) {
            Lock lock = this.mapWithLock.writeLock();
            lock.lock();

            try {
                Map<K, C> map = (Map)this.mapWithLock.getObj();
                C setWithLock = (C) map.get(key);
                if (setWithLock == null) {
                    return;
                }

                ReentrantReadWriteLock.WriteLock writeLock = setWithLock.writeLock();
                writeLock.lock();

                try {
                    CC set = (CC) setWithLock.getObj();
                    if (value != null) {
                        set.remove(value);
                    }

                    if (values != null && !values.isEmpty()) {
                        set.removeAll(values);
                    }

                    if (set.isEmpty()) {
                        map.remove(key);
                    }
                } catch (Throwable var19) {
                    log.error(var19.getMessage(), var19);
                } finally {
                    writeLock.unlock();
                }
            } catch (Throwable var21) {
                throw var21;
            } finally {
                lock.unlock();
            }

        }
    }

    public void removeItem(K key, V value) {
        this.remove(key, value, (CC) null);
    }

    public void removeItems(K key, CC values) {
        this.remove(key, (V) null, values);
    }

    public void removeValue(V value, CC values) {
        if (this.mapWithLock.size() != 0) {
            Lock lock = this.mapWithLock.writeLock();
            lock.lock();

            try {
                Map<K, C> map = (Map)this.mapWithLock.getObj();
                Set<Map.Entry<K, C>> set1 = map.entrySet();
                Set<K> keysForDel = new HashSet();
                Iterator var7 = set1.iterator();

                while(var7.hasNext()) {
                    Map.Entry<K, C> entry = (Map.Entry)var7.next();
                    K key = entry.getKey();
                    C setWithLock = (C) entry.getValue();
                    if (setWithLock != null) {
                        ReentrantReadWriteLock.WriteLock writeLock = setWithLock.writeLock();
                        writeLock.lock();

                        try {
                            CC set = (CC) setWithLock.getObj();
                            if (value != null) {
                                set.remove(value);
                            }

                            if (values != null && !values.isEmpty()) {
                                set.removeAll(values);
                            }

                            if (set.isEmpty()) {
                                keysForDel.add(key);
                            }
                        } catch (Throwable var23) {
                            log.error(var23.getMessage(), var23);
                        } finally {
                            writeLock.unlock();
                        }
                    }
                }

                if (keysForDel.size() > 0) {
                    var7 = keysForDel.iterator();

                    while(var7.hasNext()) {
                        K k = (K) var7.next();
                        map.remove(k);
                    }

                }
            } catch (Throwable var25) {
                throw var25;
            } finally {
                lock.unlock();
            }
        }
    }
}
