package org.springframework.beans.factory.config;

/**
 * @author: cp
 * @date: 2024-10-27 15:08
 * Bean 的引用
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
