package com.example.proxy.ex1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject{
    @Override
    public void Request() {
        log.info("访问真实主题方法...");
    }
}
