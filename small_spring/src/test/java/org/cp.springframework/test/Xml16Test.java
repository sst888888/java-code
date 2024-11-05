package org.cp.springframework.test;

import org.cp.springframework.context.support.ClassPathXmlApplicationContext;
import org.cp.springframework.test.bean16.IUserService;
import org.junit.Test;

/**
 * @author: cp
 * @date: 2024-11-05 15:12
 * https://github.com/fuzhengwei/small-spring/blob/main/small-spring-step-15/src/test/java/cn/bugstack/springframework/test/bean2/UserService.java
 */
public class Xml16Test {

    @Test
    public void test_autoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
