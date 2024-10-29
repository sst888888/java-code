package org.cp.springframework.test.common;

import org.cp.springframework.beans.BeansException;
import org.cp.springframework.beans.PropertyValue;
import org.cp.springframework.beans.PropertyValues;
import org.cp.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.cp.springframework.beans.factory.config.BeanDefinition;
import org.cp.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author: cp
 * @date: 2024-10-27 20:55
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company","改为：字节跳动"));
    }

}
