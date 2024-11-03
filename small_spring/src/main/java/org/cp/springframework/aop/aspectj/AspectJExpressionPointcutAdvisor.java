package org.cp.springframework.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.cp.springframework.aop.Pointcut;
import org.cp.springframework.aop.PointcutAdvisor;

/**
 * @author: cp
 * @date: 2024-11-03 18:24
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }
}
