package org.cp.springframework.test.common;

import org.cp.springframework.beans.BeansException;
import org.cp.springframework.beans.factory.config.BeanPostProcessor;
import org.cp.springframework.test.bean.UserService;

/**
 * @author: cp
 * @date: 2024-10-27 20:56
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
