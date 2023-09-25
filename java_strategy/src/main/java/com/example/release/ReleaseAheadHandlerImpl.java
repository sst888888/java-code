package com.example.release;

import com.example.enums.ReleaseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: cp
 * @date: 2023-09-25 13:41
 */
@Component
@Slf4j
public class ReleaseAheadHandlerImpl extends ReleaseHandlerImplAbstract{

    @Autowired
    private ReleaseAheadValidator releaseAheadValidator;

    @PostConstruct
    @Override
    public void init() {
        super.category = ReleaseEnum.RELEASE_AHEAD.getValue();
        super.self = this;
        super.releaseValidator = releaseAheadValidator;
        super.add2Factory();
    }

    @Override
    boolean doHandle(ReleaseDTO releaseDTO) {
        log.info("ReleaseAheadHandlerImpl doHandle. category is [{}]-[{}]", releaseDTO.getCategory(), super.category);
        return false;
    }
}
