package com.example.ob.ex1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: cp
 * @date: 2023-09-26 13:51
 */
@Slf4j
public class Observer2 implements Observer{

    private Subject subject; // 注册主题

    public Observer2(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        log.info("Observer2 get msg {}", msg);
    }
}
