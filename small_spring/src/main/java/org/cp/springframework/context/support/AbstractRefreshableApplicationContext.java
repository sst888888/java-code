package org.cp.springframework.context.support;

import org.cp.springframework.beans.BeansException;
import org.cp.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.cp.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author: cp
 * @date: 2024-10-27 20:21
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
