package com.org.creational;

/**
 * 懒汉式单例的实现  线程安全
 */
public class LazySingleton {

    // 持有一个JVM全局唯一的实例 私有的
    private static LazySingleton instance;

    // 为了防止别人随意的创建 我们需要私有化构造器
    private LazySingleton() {};

    // 暴露一个方法 用来获取实例
    public static LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
