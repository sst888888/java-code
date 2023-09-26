package com.example.cd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Computer {

    public void on() {
        log.info("打开电脑");
    }

    public void off() {
        log.info("关闭电脑");
    }

}
