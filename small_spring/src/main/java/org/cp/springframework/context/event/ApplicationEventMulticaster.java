package org.cp.springframework.context.event;

import org.cp.springframework.context.ApplicationEvent;
import org.cp.springframework.context.ApplicationListener;

/**
 * @author: cp
 * @date: 2024-10-30 15:14
 *
 *  * 事件广播器
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);

}
