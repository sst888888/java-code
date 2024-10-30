package org.cp.springframework.test;

import org.cp.springframework.aop.AdvisedSupport;
import org.cp.springframework.aop.TargetSource;
import org.cp.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.cp.springframework.aop.framework.Cglib2AopProxy;
import org.cp.springframework.aop.framework.JdkDynamicAopProxy;
import org.cp.springframework.test.bean.DemoService;
import org.cp.springframework.test.bean.DemoServiceInterceptor;
import org.cp.springframework.test.bean.IDemoService;
import org.junit.Test;

/**
 * @author: cp
 * @date: 2024-10-30 20:39
 */
public class AopTest {

    @Test
    public void test_dynamic() {
        // 目标对象
        IDemoService demoService = new DemoService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(demoService));
        advisedSupport.setMethodInterceptor(new DemoServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.cp.springframework.test.bean.IDemoService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IDemoService proxy_jdk = (IDemoService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IDemoService proxy_cglib = (IDemoService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("测试"));
    }

}
