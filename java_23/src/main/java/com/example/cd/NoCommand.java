package com.example.cd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoCommand implements Command {


    @Override
    public void execute() {
        log.info("do nothing");
    }
}
