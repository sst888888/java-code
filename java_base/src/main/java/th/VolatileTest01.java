package th;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: cp
 * @date: 2023-09-11 17:26
 * @link https://pdai.tech/md/java/thread/java-thread-x-key-volatile.html
 *  volatile不能保证完全的原子性，只能保证单次的读/写操作具有原子性。
 *  线程安全的实现：互斥同步、      非阻塞同步、  无同步方案
 *                synchronized   cas         threadLocal
 */
public class VolatileTest01 {

//    volatile int i;
    AtomicInteger i = new AtomicInteger(); // 能保证原子性

    public void addI(){
//        i++;
        i.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        final  VolatileTest01 test01 = new VolatileTest01();
        for (int n = 0; n < 1000; n++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test01.addI();
                }
            }).start();
        }
        Thread.sleep(10000);//等待10秒，保证上面程序执行完成
        System.out.println(test01.i);
    }
}
