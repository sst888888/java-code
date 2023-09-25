package com.example.release;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: cp
 * @date: 2023-09-24 23:02
 */
@Slf4j
public abstract class ReleaseHandlerImplAbstract implements ReleaseHandler{

    protected ReleaseValidator releaseValidator;

    protected Integer category;

    protected ReleaseHandler self;

    public abstract void init();

    protected void add2Factory() {
        ReleaseHandlerFactory.addHandler2Factory(category, self);
    }


    @Override
    public boolean handle(ReleaseDTO releaseDTO) {

        log.info("handle category is [{}]", releaseDTO.getCategory());

        boolean validated = releaseValidator.validate(releaseDTO); // releaseValidator为子类handler里面的releaseValidator
        if (!validated) {
            log.error("handle validated is false");
        }

        return doHandle(releaseDTO);
    }


    abstract boolean doHandle(ReleaseDTO releaseDTO);
}
