package com.example.risk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@EventType(EventTypeConstant.SINGLE_PAY)
@Slf4j
public class SinglePayStrategyImpl implements Strategy{
    @Override
    public void execute() {
        log.info("SinglePayStrategyImpl execute ......");
    }
}
