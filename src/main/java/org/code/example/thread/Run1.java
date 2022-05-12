package org.code.example.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Run1
 * @Description
 * @Author Chaiphat
 * @Date 2020/2/18 20:10
 * @Version 1.0
 **/
public class Run1 implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Run1.class);

    @Override
    public void run() {
        int count = 0;
        while (true) {
            count++;
            logger.info("===22==={}", count);

            if (count == 10) {
                try {
                    logger.info("===={}====", 1 / 0);
                } catch (Throwable e) {
                    logger.error("Exception:{}", e);
                }
            }

            if (count == 20) {
                logger.info("count={}", count);
                break;
            }
        }
    }
}
