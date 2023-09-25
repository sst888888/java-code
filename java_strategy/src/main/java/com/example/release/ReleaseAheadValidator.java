package com.example.release;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: cp
 * @date: 2023-09-25 13:32
 */
@Component
@Slf4j
public class ReleaseAheadValidator extends ReleaseValidatorAbstract{

    @Override
    public boolean validate(ReleaseDTO releaseDTO) {
        boolean validated = super.validate(releaseDTO);
        if (validated) {
            log.info("ReleaseAheadValidator execute validate ......");
        }
        return validated;
    }
}
