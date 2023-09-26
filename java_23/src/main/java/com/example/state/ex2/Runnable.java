package com.example.state.ex2;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Runnable extends ThreadState {
    public Runnable() {
        super.stateName = "就绪状态";
        log.info("当前线程处于：就绪状态.");
    }

    public void getCPU(ThreadContext hj) {
        log.info("获得CPU时间-->");
        if (stateName.equals("就绪状态")) {
            hj.setState(new Running());
        } else {
            log.info("当前线程不是就绪状态，不能获取CPU.");
        }
    }
}
