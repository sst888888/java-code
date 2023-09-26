package com.example.state.ex2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class New extends ThreadState{

    public New() {
        super.stateName = "新建状态";
        log.info("当前线程处于：新建状态.");
    }

    public void start(ThreadContext hj) {
        log.info("调用start()方法-->");
        if (stateName.equals("新建状态")) {
            hj.setState(new Runnable());
        } else {
            log.info("当前线程不是新建状态，不能调用start()方法.");
        }
    }

}
