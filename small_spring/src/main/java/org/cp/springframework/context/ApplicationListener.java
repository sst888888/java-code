package org.cp.springframework.context;

import java.util.EventListener;

/**
 * @author: cp
 * @date: 2024-10-30 15:15
 */
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
