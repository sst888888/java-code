package com.example.demo;

import org.code.example.spring.BeanConfig;
import org.code.example.spring.BusinessEvent;
import org.code.example.spring.BusinessPublisher;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName Test
 * @Description 用于测试spring事件监听
 * @Author Chaiphat
 * @Date 2020/5/3 14:58
 * @Version 1.0
 **/
public class TestSpringPublish {

    @Test
    public void test() {
        //创建springIOC容器
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        //从容器中获取事件发布器实例
        BusinessPublisher businessPublisher = applicationContext.getBean(BusinessPublisher.class);
        //创建事件
        BusinessEvent businessEvent = new BusinessEvent(new TestSpringPublish(), "tttt");
        //发布事件
        businessPublisher.publishEvent(businessEvent);
    }

}
