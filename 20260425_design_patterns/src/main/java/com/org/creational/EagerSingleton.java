package com.org.creational;

/**
 * 饿汉式单例的实现  线程安全
 */
public class EagerSingleton {

    // 持有一个JVM全局唯一的实例 私有的
    private final static EagerSingleton instance = new EagerSingleton();

    // 为了防止别人随意的创建 我们需要私有化构造器
    private EagerSingleton() {};

    // 暴露一个方法 用来获取实例
    public static EagerSingleton getInstance() {
        return instance;
    }

}
