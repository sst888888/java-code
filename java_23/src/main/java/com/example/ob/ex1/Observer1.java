package com.example.ob.ex1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: cp
 * @date: 2023-09-26 13:51
 */
@Slf4j
public class Observer1 implements Observer{

    private Subject subject; // 注册主题

    public Observer1(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        log.info("Observer1 get msg {}", msg);
    }
}
