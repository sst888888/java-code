package org.cp.springframework.beans.factory.config;

/**
 * @author: cp
 * @date: 2024-10-25 20:18
 * 单例注册表
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
