package org.cp.springframework.context.support;

import org.cp.springframework.beans.BeansException;
import org.cp.springframework.beans.factory.config.BeanPostProcessor;
import org.cp.springframework.context.ApplicationContext;
import org.cp.springframework.context.ApplicationContextAware;

/**
 * @author: cp
 * @date: 2024-10-29 20:21
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
