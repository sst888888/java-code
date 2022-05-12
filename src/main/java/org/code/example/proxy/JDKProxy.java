package org.code.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName JDKProxy
 * @Description JDK代理类生成
 * @Author Chaiphat
 * @Date 2020/3/4 17:07
 * @Version 1.0
 **/
public class JDKProxy implements InvocationHandler {

    private Object target;

    public JDKProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return ((ProHello)target).invoke();
    }
}
