package org.cp.springframework.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.cp.springframework.aop.AdvisedSupport;
import org.cp.springframework.aop.ClassFilter;
import org.cp.springframework.aop.MethodMatcher;
import org.cp.springframework.aop.TargetSource;
import org.cp.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.cp.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.cp.springframework.aop.framework.ProxyFactory;
import org.cp.springframework.aop.framework.ReflectiveMethodInvocation;
import org.cp.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.cp.springframework.context.support.ClassPathXmlApplicationContext;
import org.cp.springframework.test.bean.Demo12Service;
import org.cp.springframework.test.bean.Demo12ServiceBeforeAdvice;
import org.cp.springframework.test.bean.Demo12ServiceInterceptor;
import org.cp.springframework.test.bean.IDemo12Service;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: cp
 * @date: 2024-11-03 19:37
 */
public class Xml13Test {

    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        // 目标对象
        IDemo12Service userService = new Demo12Service();
        // 组装代理信息
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new Demo12ServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.cp.springframework.test.bean.IDemo12Service.*(..))"));
    }

    @Test
    public void test_proxyFactory() {
        advisedSupport.setProxyTargetClass(false); // false/true，JDK动态代理、CGlib动态代理
        IDemo12Service proxy = (IDemo12Service) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void test_beforeAdvice() {
        Demo12ServiceBeforeAdvice beforeAdvice = new Demo12ServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        IDemo12Service proxy = (IDemo12Service) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void test_advisor() {
        // 目标对象
        IDemo12Service userService = new Demo12Service();

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* org.cp.springframework.test.bean.IDemo12Service.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new Demo12ServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(userService);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true); // false/true，JDK动态代理、CGlib动态代理

            IDemo12Service proxy = (IDemo12Service) new ProxyFactory(advisedSupport).getProxy();
            System.out.println("测试结果：" + proxy.queryUserInfo());
        }
    }

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        IDemo12Service userService = applicationContext.getBean("demo12Service", IDemo12Service.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    @Test
    public void test_proxy_method() {
        // 目标对象(可以替换成任何的目标对象)
        Object targetObj = new Demo12Service();

        // AOP 代理
        IDemo12Service proxy = (IDemo12Service) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.cp.springframework.test.bean.IDemo12Service.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });

        String result = proxy.queryUserInfo();
        System.out.println("测试结果：" + result);

    }

}
