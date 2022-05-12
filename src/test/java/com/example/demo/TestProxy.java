package com.example.demo;


import org.code.example.proxy.Hello;
import org.code.example.proxy.JDKProxy;
import org.code.example.proxy.ProHello;
import org.junit.Test;
import org.junit.platform.commons.util.ClassLoaderUtils;

import java.lang.reflect.Proxy;

/**
 * @ClassName TestProxy
 * @Description 测试例子
 * @Author Chaiphat
 * @Date 2020/3/4 17:11
 * @Version 1.0
 **/
public class TestProxy {

    @Test
    public void test() {
        // 构建代理器 被代理对象不同 结果不同
        JDKProxy proxy = new JDKProxy(new ProHello());
        ClassLoader classLoader = ClassLoaderUtils.getDefaultClassLoader();
        // 把生成的代理类保存到文件
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 生成代理类
        Hello entity = (Hello) Proxy.newProxyInstance(classLoader, new Class[]{Hello.class}, proxy);
        System.out.println(entity.say());
    }

}
