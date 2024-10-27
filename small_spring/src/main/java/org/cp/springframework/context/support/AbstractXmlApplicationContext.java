package org.cp.springframework.context.support;


import org.cp.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.cp.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author: cp
 * @date: 2024-10-27 20:25
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}
