package org.cp.springframework.beans.factory;

/**
 * @author: cp
 * @date: 2024-10-29 17:21
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
