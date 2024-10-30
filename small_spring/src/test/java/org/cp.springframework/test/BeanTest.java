package org.cp.springframework.test;

import org.junit.Test;
import org.cp.springframework.beans.PropertyValue;
import org.cp.springframework.beans.PropertyValues;
import org.cp.springframework.beans.factory.config.BeanDefinition;
import org.cp.springframework.beans.factory.config.BeanReference;
import org.cp.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.cp.springframework.test.bean.UserDao;
import org.cp.springframework.test.bean.UserService;

/**
 * @author: cp
 * @date: 2024-10-27 15:18
 */
public class BeanTest {

//    @Test
//    public void test_BeanFactory() {
//        // 1.初始化 BeanFactory
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//        // 2. UserDao 注册
//        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
//
//        // 3. UserService 设置属性[uId、userDao]
//        PropertyValues propertyValues = new PropertyValues();
//        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
//        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
//
//        // 4. UserService 注入bean
//        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
//        beanFactory.registerBeanDefinition("userService", beanDefinition);
//
//        // 5. UserService 获取bean
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfoByDao();
//    }

}
