package org.cp.springframework.aop;

/**
 * @author: cp
 * @date: 2024-10-30 18:53
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);


}
