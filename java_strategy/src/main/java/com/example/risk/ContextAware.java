package com.example.risk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;


@Slf4j
@Component
public class ContextAware implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 获取所有策略注解的Bean
        Map<String, Object> riskStrategyMap = applicationContext.getBeansWithAnnotation(EventType.class);
        if (riskStrategyMap.size() == 0) {
            log.info("当前未找到策略注解的类...");
            return;
        }

        riskStrategyMap.forEach((k, v) -> {
            String key = v.getClass().getAnnotation(EventType.class).value();
            if (StrategyHandler.strategyBeanMap.containsKey(key)) {
                log.error("风控策略存在相同的[风控事件类型: {}]处理类，请先修复代码！", key);
                throw new RuntimeException("风控策略存在相同的[风控事件类型: " + key + "]处理类，请先修复代码！");
            }

            StrategyHandler.strategyBeanMap.put(key, (Strategy) v);
        });

        log.info("初始化风控策略集合完毕: {}", StrategyHandler.strategyBeanMap.keySet());
    }
}