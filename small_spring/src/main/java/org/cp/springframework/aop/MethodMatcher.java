package org.cp.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author: cp
 * @date: 2024-10-30 18:54
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);
}
