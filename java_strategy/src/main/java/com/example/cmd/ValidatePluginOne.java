package com.example.cmd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Slf4j
@Component
public class ValidatePluginOne extends ValidatePlugin implements InitializingBean {
    @Override
    public void validate() {
        log.info("validatePluginOne 规则校验 ......");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        ValidateFactory.register(1, this);
        ValidateFactory.add(this);
        validatePlugins.add(this);
    }

    @Override
    public Object call() throws Exception {
        validate();
        return null;
    }
}
