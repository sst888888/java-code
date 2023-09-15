package th.aqs;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.locks.Lock;


/**
 * @author: cp
 * @date: 2023-09-13 17:10
 */
@Slf4j
public class MyThread3 extends Thread{

    private Lock lock;
    public MyThread3(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    public void run () {
        lock.lock();
        try {
            log.info(Thread.currentThread() + " running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

}
