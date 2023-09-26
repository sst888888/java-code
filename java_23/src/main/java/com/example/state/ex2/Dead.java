package com.example.state.ex2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dead extends ThreadState {
    public Dead() {
        super.stateName = "死亡状态";
        log.info("当前线程处于：死亡状态.");
    }
}
