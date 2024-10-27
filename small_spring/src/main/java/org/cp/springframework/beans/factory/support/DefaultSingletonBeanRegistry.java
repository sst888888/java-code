package org.cp.springframework.beans.factory.support;

import org.cp.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cp
 * @date: 2024-10-25 20:20
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
