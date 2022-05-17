package org.code.example.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName JoinDemo
 * @Description
 * @Author Chaiphat
 * @Date 2020/2/18 16:13
 * @Version 1.0
 **/
public class JoinDemo {

    private static final Logger logger = LoggerFactory.getLogger(JoinDemo.class);

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            logger.info("running1");
            try {
                Thread.sleep(3000);
                logger.info("thread1 end");
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            logger.info("running2");
            try {
                Thread.sleep(2000);
                logger.info("thread2 end");
            }catch (InterruptedException e){
                logger.info(e.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();

        // 主线程等待子线程结束
        t1.join();
        t2.join();

        logger.info("end.........");
    }


}
