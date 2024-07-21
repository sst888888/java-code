package com.example.command;

import lombok.extern.slf4j.Slf4j;

/**
 * 定义接受者 公公的角色
 */
@Slf4j
public class Receiver {
    public void charge() {
        log.info("收取奏折");
    }

    public void issue() {
        log.info("颁布圣旨");
    }
}
