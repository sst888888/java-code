package org.cp.springframework.test.event;

import org.cp.springframework.context.ApplicationListener;
import org.cp.springframework.context.event.ContextRefreshedEvent;

/**
 * @author: cp
 * @date: 2024-10-30 17:06
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
