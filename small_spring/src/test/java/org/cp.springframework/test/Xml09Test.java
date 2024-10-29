package org.cp.springframework.test;

import org.cp.springframework.context.support.ClassPathXmlApplicationContext;
import org.cp.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author: cp
 * @date: 2024-10-29 18:34
 */
public class Xml09Test {

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo07();
        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContextAware："+userService.getApplicationContext());
        System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }

}
