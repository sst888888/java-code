package org.cp.springframework.beans.factory;

/**
 * @author: cp
 * @date: 2024-10-29 20:04
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);
}
