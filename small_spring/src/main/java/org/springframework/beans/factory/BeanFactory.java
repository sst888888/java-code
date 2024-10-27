package org.springframework.beans.factory;


import org.springframework.beans.BeansException;

/**
 * @author: cp
 * @date: 2024-10-25 20:15
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
