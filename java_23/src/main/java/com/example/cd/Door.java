package com.example.cd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Door {


    public void open() {
        log.info("打开门");
    }

    public void close() {
        log.info("关闭门");
    }

}
