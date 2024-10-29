package org.cp.springframework.context;

import org.cp.springframework.beans.BeansException;
import org.cp.springframework.beans.factory.Aware;

/**
 * @author: cp
 * @date: 2024-10-29 20:13
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
