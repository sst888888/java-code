package org.cp.springframework.aop;

/**
 * @author: cp
 * @date: 2024-11-03 18:22
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();
}
