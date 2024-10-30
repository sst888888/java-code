package org.cp.springframework.context.event;

import org.cp.springframework.context.ApplicationContext;
import org.cp.springframework.context.ApplicationEvent;

/**
 * @author: cp
 * @date: 2024-10-30 15:04
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }


    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
