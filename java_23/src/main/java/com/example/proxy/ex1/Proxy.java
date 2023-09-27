package com.example.proxy.ex1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Proxy implements Subject{

    private RealSubject realSubject;


    @Override
    public void Request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.Request();
        postRequest();
    }

    public void preRequest() {
        log.info("访问真实主题之前的预处理");
    }
    public void postRequest() {
        log.info("访问真实主题之后的后续处理");
    }

}
