package org.cp.springframework.context;

import java.util.EventObject;

/**
 * @author: cp
 * @date: 2024-10-30 15:03
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
