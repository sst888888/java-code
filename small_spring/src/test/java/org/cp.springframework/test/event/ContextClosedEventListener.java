package org.cp.springframework.test.event;

import org.cp.springframework.context.ApplicationListener;
import org.cp.springframework.context.event.ContextClosedEvent;

/**
 * @author: cp
 * @date: 2024-10-30 17:06
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
