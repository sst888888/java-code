package org.code.example.spring;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName BusinessPublisher
 * @Description spring事件监听机制中的"事件发布器"
 * @Author Chaiphat
 * @Date 2020/5/3 14:53
 * @Version 1.0
 **/
@Slf4j
@Component
public class BusinessPublisher implements ApplicationEventPublisherAware {
    private static final Logger logger = LoggerFactory.getLogger(BusinessPublisher.class);

    // spring提供的事件发布组件
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(BusinessEvent businessEvent){
        logger.info("发布事件：{} ", businessEvent.getType());
        this.applicationEventPublisher.publishEvent(businessEvent);
    }
}
