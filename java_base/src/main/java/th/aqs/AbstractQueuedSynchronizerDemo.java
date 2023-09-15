package th.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: cp
 * @date: 2023-09-15 15:33
 */
public class AbstractQueuedSynchronizerDemo {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock(true);

        MyThread3 t1 = new MyThread3("t1", lock);
        MyThread3 t2 = new MyThread3("t2", lock);
        MyThread3 t3 = new MyThread3("t3", lock);
        t1.start();
        t2.start();
        t3.start();
    }

}
