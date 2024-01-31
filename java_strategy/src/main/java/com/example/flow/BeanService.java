package com.example.flow;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanService implements ApplicationContextAware {

    protected static ApplicationContext applicationContext = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        BeanService.applicationContext = applicationContext;
        applicationContext = applicationContext;
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    }

    public static Object getBeanByName(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getSingleBeanByType(Class<T> clazz) throws Exception{
      ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath/applicationContext.xml");

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            Object beanByName = applicationContext.getBean(beanName);
            if (beanByName != null) {
//                AopTargetUtil.get
                if (clazz.getName().contains(beanName)) {
                    return (T) beanByName;
                }
            }
        }

        throw new Exception();
    }


}
