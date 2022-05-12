package org.code.example.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName StopThread
 * @Description
 * @Author Chaiphat
 * @Date 2020/2/18 17:53
 * @Version 1.0
 **/
public class StopThread implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(StopThread.class);

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            logger.info("{} running ", Thread.currentThread().getName());
        }
        logger.info("{} end ", Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new StopThread(), "thread A");
        threadA.start();

        TimeUnit.MICROSECONDS.sleep(1);

        logger.info("main 线程执行");

        threadA.interrupt();
    }
}
