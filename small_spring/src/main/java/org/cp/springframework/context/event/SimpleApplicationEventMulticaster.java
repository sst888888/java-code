package org.cp.springframework.context.event;

import org.cp.springframework.beans.factory.BeanFactory;
import org.cp.springframework.context.ApplicationEvent;
import org.cp.springframework.context.ApplicationListener;

/**
 * @author: cp
 * @date: 2024-10-30 15:51
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
