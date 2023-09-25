package com.example.risk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@EventType(EventTypeConstant.TOTAL_AMOUNT)
@Slf4j
public class TotalAmountStrategyImpl implements Strategy{
    @Override
    public void execute() {
        log.info("TotalAmountStrategyImpl execute ......");
    }
}
