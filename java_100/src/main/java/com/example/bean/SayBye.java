package com.example.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

// 全局bean ConfigurableBeanFactory.SCOPE_PROTOTYPE
// 以代理方式注入 proxyMode = ScopedProxyMode.TARGET_CLASS
@Service
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SayBye extends SayService {

    @Override
    public void say() {
        super.say();
        log.info("bye");
    }
}
