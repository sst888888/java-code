package org.cp.springframework.context;

/**
 * @author: cp
 * @date: 2024-10-30 15:45
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);

}
