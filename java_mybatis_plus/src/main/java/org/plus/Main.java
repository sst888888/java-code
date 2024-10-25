package org.plus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: cp
 * @date: 2024-01-18 19:07
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Hello world!");
        logger.info("这是一个 info 级别的日志消息。");
        logger.debug("这是一个 debug 级别的日志消息。");
        logger.error("这是一个 error 级别的日志消息。");
    }
}