package org.cp.springframework.beans.factory;

import org.cp.springframework.beans.BeansException;

/**
 * @author: cp
 * @date: 2024-10-29 20:05
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
