package com.example.risk;


import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class StrategyHandler {

    public static Map<String, Strategy> strategyBeanMap = Maps.newConcurrentMap();

    public static void exe(ReqVo reqVo) {
        Strategy strategy = strategyBeanMap.get(reqVo.getCategory());
        strategy.execute();
    }

}
