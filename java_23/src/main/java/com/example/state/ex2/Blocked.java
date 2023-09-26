package com.example.state.ex2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Blocked extends ThreadState {
    public Blocked() {
        super.stateName = "阻塞状态";
        log.info("当前线程处于：阻塞状态.");
    }
    public void resume(ThreadContext hj) {
        log.info("调用resume()方法-->");
        if (stateName.equals("阻塞状态")) {
            hj.setState(new Runnable());
        } else {
            log.info("当前线程不是阻塞状态，不能调用resume()方法.");
        }
    }
}
