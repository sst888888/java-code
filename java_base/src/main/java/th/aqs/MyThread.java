package th.aqs;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: cp
 * @date: 2023-09-13 17:10
 */
@Slf4j
public class MyThread extends Thread{

    public void run() {
        synchronized (this) {
            log.info("before notify");
            // 释放锁 主线程获得锁
            notify();
            log.info("after notify");
        }
    }
}
