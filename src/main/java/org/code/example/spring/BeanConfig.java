package org.code.example.spring;

import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName BeanConfig
 * @Description spring容器配置类
 * 需要在容器中注册事件监听器、事件发布器
 * @Author Chaiphat
 * @Date 2020/5/3 14:56
 * @Version 1.0
 **/
@ComponentScan(basePackages = {"org.code.example.spring"})
public class BeanConfig {
}
