package com.example.demo;

import org.code.example.thread.Run1;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadExceptionTest
 * @Description 异常测试
 * @Author Chaiphat
 * @Date 2020/2/18 20:04
 * @Version 1.0
 **/
public class TestThreadException {

    private static final Logger logger = LoggerFactory.getLogger(TestThreadException.class);

    @Test
    public void test() throws InterruptedException{
        ThreadPoolExecutor execute = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));

        execute.execute(()->{
            logger.info("===11===");
        });

        TimeUnit.SECONDS.sleep(5);

        execute.execute(new Run1());
    }


}
