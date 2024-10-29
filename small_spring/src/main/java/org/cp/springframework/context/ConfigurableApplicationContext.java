package org.cp.springframework.context;

import org.cp.springframework.beans.BeansException;

/**
 * @author: cp
 * @date: 2024-10-27 19:54
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
