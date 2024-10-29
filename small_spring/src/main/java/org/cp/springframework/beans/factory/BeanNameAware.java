package org.cp.springframework.beans.factory;

/**
 * @author: cp
 * @date: 2024-10-29 20:06
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
