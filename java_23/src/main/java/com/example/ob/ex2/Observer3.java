package com.example.ob.ex2;

import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

// @link https://blog.csdn.net/lmj623565791/article/details/24179699
@Slf4j
public class Observer3 implements Observer {

    // 注册主题
    public void registerSubject(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Subject43D) {
            Subject43D subject43D = (Subject43D)o;
            log.info("Subject43D msg is {}", subject43D.getMsg());
        }

        if (o instanceof Subject4SSQ) {
            Subject4SSQ subject4SSQ = (Subject4SSQ)o;
            log.info("Subject4SSQ msg is {}", subject4SSQ.getMsg());
        }
    }
}
