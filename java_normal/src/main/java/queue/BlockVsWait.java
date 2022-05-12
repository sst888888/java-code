package queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName BlockVsWait
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/21 17:16
 * @Version 1.0
 **/
@Slf4j
public class BlockVsWait {

    Object locker = new Object();
    ArrayBlockingQueue<Integer> arrayBlockingQueue1 = new ArrayBlockingQueue<>(1);
    ArrayBlockingQueue<Integer> arrayBlockingQueue2 = new ArrayBlockingQueue<>(1);

    @Test
    public void test() throws InterruptedException {
        arrayBlockingQueue1.put(1);
        Thread waitOnTake = new Thread(() -> {
            synchronized (locker) {
                try {
                    arrayBlockingQueue2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        waitOnTake.setName("waitOnTake");
        waitOnTake.start();

        Thread waitOnPut = new Thread(() -> {
            synchronized (locker) {
                try {
                    arrayBlockingQueue1.put(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        waitOnPut.setName("waitOnPut");
        waitOnPut.start();

        Thread block = new Thread(() -> {
            synchronized (locker) {
                log.info("OK");
            }
        });

        block.setName("block");
        block.start();

        block.join();
    }
}
