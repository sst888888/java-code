package com.org.creational;

/**
 * 双重检查锁单例的实现  线程安全
 */
public class DclSingleton {
    // volatile 禁止指令重排序问题 1.9以上 可以不加 jvm内部处理有序
    // 创建对象不是一个原子性操作
    private volatile static DclSingleton singleton;
    private DclSingleton (){}

    public static DclSingleton getInstance() {
        if (singleton == null) {
            synchronized (DclSingleton.class) {
                if (singleton == null) {
                    singleton = new DclSingleton();
                }
            }
        }
        return singleton;
    }
}
