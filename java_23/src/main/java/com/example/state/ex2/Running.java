package com.example.state.ex2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Running extends ThreadState {
    public Running() {
        super.stateName = "运行状态";
        log.info("当前线程处于：运行状态.");
    }
    public void suspend(ThreadContext hj) {
        log.info("调用suspend()方法-->");
        if (stateName.equals("运行状态")) {
            hj.setState(new Blocked());
        } else {
            log.info("当前线程不是运行状态，不能调用suspend()方法.");
        }
    }
    public void stop(ThreadContext hj) {
        log.info("调用stop()方法-->");
        if (stateName.equals("运行状态")) {
            hj.setState(new Dead());
        } else {
            log.info("当前线程不是运行状态，不能调用stop()方法.");
        }
    }
}
