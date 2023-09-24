package com.example.cancel;

import com.example.enums.CancelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: cp
 * @date: 2023-09-24 22:41
 */
@Component
@Slf4j
public class PayTimeoutImpl implements CancelOrderService, InitializingBean {
    @Override
    public boolean exeCancelOrder() {
        log.info("PayTimeoutImpl exeCancelOrder ......");
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CancelOrderServiceStrategyFactory.register(CancelEnum.PAY_TIMEOUT.getValue(), this);
    }
}
