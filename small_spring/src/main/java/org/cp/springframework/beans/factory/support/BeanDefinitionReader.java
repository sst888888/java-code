package org.cp.springframework.beans.factory.support;

import org.cp.springframework.beans.BeansException;
import org.cp.springframework.core.io.Resource;
import org.cp.springframework.core.io.ResourceLoader;

/**
 * @author: cp
 * @date: 2024-10-27 17:15
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
