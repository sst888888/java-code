package org.code.example.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName VolatileDemo
 * @Description 用主线程关闭A线程
 * @Author Chaiphat
 * @Date 2020/2/18 16:33
 * @Version 1.0
 **/
public class VolatileDemo implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(VolatileDemo.class);

    // volatile 保证内存可见
    private static volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            logger.info("{}正在执行", Thread.currentThread().getName());
        }
        logger.info("{}执行完毕", Thread.currentThread().getName());
    }

    private void stopThread() {
        flag = false;
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        new Thread(volatileDemo, "Thread A").start();

        logger.info("main线程正在执行");
        try {
            TimeUnit.MICROSECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        volatileDemo.stopThread();
    }
}
