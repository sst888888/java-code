package com.example.cancel;

import com.example.enums.CancelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: cp
 * @date: 2023-09-24 22:44
 */
@Component
@Slf4j
public class AbnormalImpl implements CancelOrderService, InitializingBean {
    @Override
    public boolean exeCancelOrder() {
        log.info("AbnormalImpl exeCancelOrder ......");
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CancelOrderServiceStrategyFactory.register(CancelEnum.ABNORMAL.getValue(), this);
    }

}
