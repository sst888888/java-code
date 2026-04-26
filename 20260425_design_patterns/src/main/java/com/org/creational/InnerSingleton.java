package com.org.creational;

/**
 * 静态内部类
 */
public class InnerSingleton {
    // 构造器私有化
    private InnerSingleton(){}

    // 提供一个方法获取单例
    public InnerSingleton getInstance() {
        return InnerSingletonHolder.INSTANCE;
    }

    // 类加载的时间 一个类会在第一次主动使用的时候被加载
    private static class InnerSingletonHolder{
        private static final InnerSingleton INSTANCE = new InnerSingleton();
    }
}
