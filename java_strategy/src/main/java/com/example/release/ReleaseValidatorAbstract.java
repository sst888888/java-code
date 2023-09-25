package com.example.release;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: cp
 * @date: 2023-09-25 13:27
 */
@Slf4j
public class ReleaseValidatorAbstract implements ReleaseValidator{
    @Override
    public boolean validate(ReleaseDTO releaseDTO) {
        log.info("ReleaseValidatorAbstract validate result is true");
        return true;
    }
}
