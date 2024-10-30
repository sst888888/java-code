package org.cp.springframework.test.event;

import org.cp.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author: cp
 * @date: 2024-10-30 17:04
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }

}