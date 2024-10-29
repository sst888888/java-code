package org.cp.springframework.beans.factory;

/**
 * @author: cp
 * @date: 2024-10-29 17:17
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
