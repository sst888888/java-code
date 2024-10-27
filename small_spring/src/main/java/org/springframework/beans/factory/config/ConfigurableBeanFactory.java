package org.springframework.beans.factory.config;

import org.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author: cp
 * @date: 2024-10-27 16:55
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
