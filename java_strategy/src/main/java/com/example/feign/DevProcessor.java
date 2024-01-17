package com.example.feign;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author: feign动态配置开发模式URL
 * @date: 2024-01-17 17:20
 * @link https://blog.csdn.net/qq_36850300/article/details/125000814
 */
@Configuration
@ConditionalOnProperty(value = "spring.profiles.active", havingValue = "dev")
public class DevProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // 设置微服务开发模式下的IP端口信息
        System.setProperty("feign.debug.url.base", "http://127.0.0.1:9000");
        System.setProperty("feign.debug.url.monitor", "http://127.0.0.1:9001");
        System.setProperty("feign.debug.url.system", "http://127.0.0.1:9002");

        // 不注册到nacos上 @FeignClient调用指定了URL就不会再从nacos上拉取服务信息走负载均衡
        System.setProperty("spring.cloud.nacos.discovery.register-enabled", "false");
        return bean;
    }
}
