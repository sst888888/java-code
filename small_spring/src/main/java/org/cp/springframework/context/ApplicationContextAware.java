package org.cp.springframework.context;

import org.cp.springframework.beans.BeansException;
import org.cp.springframework.beans.factory.Aware;

/**
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link ApplicationContext} that it runs in.
 *
 * 实现此接口，既能感知到所属的 ApplicationContext
 *
 *
 *
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
    