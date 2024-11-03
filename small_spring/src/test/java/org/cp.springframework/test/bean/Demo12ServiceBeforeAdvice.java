package org.cp.springframework.test.bean;

import org.cp.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: cp
 * @date: 2024-11-03 19:31
 */
public class Demo12ServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }

}
