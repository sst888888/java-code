package org.cp.springframework.beans.factory;

/**
 * @author: cp
 * @date: 2024-10-29 22:08
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
