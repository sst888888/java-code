package com.example.release;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: cp
 * @date: 2023-09-25 13:29
 */
@Slf4j
@Component
public class ReleaseNormalValidator extends ReleaseValidatorAbstract{
    @Override
    public boolean validate(ReleaseDTO releaseDTO) {
        boolean validated = super.validate(releaseDTO);
        if (validated) {
            log.info("ReleaseNormalValidator execute validate ......");
        }
        return validated;
    }
}
