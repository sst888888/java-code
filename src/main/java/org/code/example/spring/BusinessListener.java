package org.code.example.spring;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName BusinessListener
 * @Description spring监听机制中"监听器" 监听具体的事件BusinessEvent
 * @Author Chaiphat
 * @Date 2020/5/3 14:51
 * @Version 1.0
 **/
@Slf4j
@Component
public class BusinessListener implements ApplicationListener<BusinessEvent> {
    private static final Logger logger = LoggerFactory.getLogger(BusinessListener.class);

    /**
     * 监听到事件后做的处理
     *
     * @param businessEvent
     */
    @Override
    public void onApplicationEvent(BusinessEvent businessEvent) {
        logger.info("监听到事件：{}", businessEvent.getType());
    }
}
