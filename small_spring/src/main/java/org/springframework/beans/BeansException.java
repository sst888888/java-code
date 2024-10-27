package org.springframework.beans;

/**
 * @author: cp
 * @date: 2024-10-25 20:06
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
