package org.cp.springframework.test;

import org.cp.springframework.context.support.ClassPathXmlApplicationContext;
import org.cp.springframework.test.event.CustomEvent;
import org.junit.Test;

/**
 * @author: cp
 * @date: 2024-10-30 17:07
 */
public class EventTest {

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

}
