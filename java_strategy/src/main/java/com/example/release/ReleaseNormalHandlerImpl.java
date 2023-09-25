package com.example.release;

import com.example.enums.ReleaseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: cp
 * @date: 2023-09-25 13:34
 */
@Component
@Slf4j
public class ReleaseNormalHandlerImpl extends ReleaseHandlerImplAbstract{

    @Autowired
    private ReleaseNormalValidator releaseNormalValidator;
    @PostConstruct
    @Override
    public void init() {
        super.category = ReleaseEnum.RELEASE_NORMAL.getValue();
        super.self = this;
        super.releaseValidator = releaseNormalValidator;
        super.add2Factory();
    }

    @Override
    boolean doHandle(ReleaseDTO releaseDTO) {
        log.info("ReleaseNormalHandlerImpl doHandle. category is [{}]-[{}]", releaseDTO.getCategory(), super.category);
        return false;
    }

    @Override
    public boolean handle2(ReleaseDTO releaseDTO) {
        return false;
    }
}
