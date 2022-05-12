package queue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronousQueueTest
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/21 21:06
 * @Version 1.0
 **/
public class SynchronousQueueTest {

    @Test
    public void test() throws InterruptedException {
        // 公平模式 FIFO
        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);
        List<Worker> workers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String name = "Producer" + i;
            Producer worker = new Producer(name, queue);
            workers.add(worker);
            Thread thread = new Thread(worker);
            thread.setName(name);
            threads.add(thread);
            thread.start();
        }
        for (int i = 0; i < 4; i++) {
            String name = "Consumer" + i;
            Consumer worker = new Consumer(name, queue);
            workers.add(worker);
            Thread thread = new Thread(worker);
            thread.setName(name);
            threads.add(thread);
            thread.start();
        }

        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            for (Worker worker : workers) {
                worker.stop();
            }
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }, 2, TimeUnit.SECONDS);

        for (Thread thread : threads) {
            thread.join();
        }
    }

}
