package th.aqs;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: cp
 * @date: 2023-09-13 17:08
 * @link https://pdai.tech/md/java/thread/java-thread-x-lock-LockSupport.html
 */
@Slf4j
public class WaitAndNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
//        MyThread myThread = new MyThread();
//        synchronized (myThread) {
//            try {
//                myThread.start();
//                // 主线程睡眠3s
//                Thread.sleep(2000);
//                log.info("before wait");
//                // 阻塞主线程 获得锁 主线程阻塞
//                // wait()必须在synchronized块中调用
//                myThread.wait();
//                log.info("after wait");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        // 由于先调用了notify，再调用的wait，此时主线程还是会一直阻塞。
        MyThread myThread = new MyThread();
        myThread.start();
        // 主线程睡眠3s
        Thread.sleep(3000);
        synchronized (myThread) {
            try {
                log.info("before wait");
                // 阻塞主线程
                myThread.wait();
                log.info("after wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
