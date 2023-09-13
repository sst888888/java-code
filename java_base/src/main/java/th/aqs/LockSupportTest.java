package th.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: cp
 * @date: 2023-09-13 17:50
 */
@Slf4j
public class LockSupportTest {

    public static void main(String[] args) {
        MyThread2 myThread = new MyThread2(Thread.currentThread());
        myThread.start();
        log.info("before park");
        // 获取许可
        LockSupport.park("ParkAndUnparkDemo");
        log.info("after park");
    }

}
