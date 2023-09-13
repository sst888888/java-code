package th.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: cp
 * @date: 2023-09-13 17:48
 */
@Slf4j
public class MyThread2 extends Thread{

    private Object object;

    public MyThread2(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        log.info("before unpark");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取blocker
        log.info("Blocker info " + LockSupport.getBlocker((Thread) object));
        // 释放许可
        LockSupport.unpark((Thread) object);
        // 休眠500ms，保证先执行park中的setBlocker(t, null);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再次获取blocker
        log.info("Blocker info " + LockSupport.getBlocker((Thread) object));
        log.info("after unpark");
    }

}
