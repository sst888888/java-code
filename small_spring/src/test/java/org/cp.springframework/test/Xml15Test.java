package org.cp.springframework.test;

import org.cp.springframework.context.support.ClassPathXmlApplicationContext;
import org.cp.springframework.test.bean15.IUserService;
import org.junit.Test;


public class Xml15Test {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
