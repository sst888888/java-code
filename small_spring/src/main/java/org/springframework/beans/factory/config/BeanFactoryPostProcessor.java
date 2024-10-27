package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * @author: cp
 * @date: 2024-10-27 19:33
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;


}
