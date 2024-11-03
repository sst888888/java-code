package org.cp.springframework.aop;
import org.aopalliance.aop.Advice;


/**
 * @author: cp
 * @date: 2024-11-03 18:18
 */
public interface Advisor {

    Advice getAdvice();

}
