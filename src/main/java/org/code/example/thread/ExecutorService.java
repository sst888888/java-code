package org.code.example.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ExecutorService
 * @Description
 * @Author Chaiphat
 * @Date 2020/2/18 18:02
 * @Version 1.0
 **/
public class ExecutorService {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 50, 5, TimeUnit.SECONDS,
//            new ArrayBlockingQueue<>(1000000),
            new SynchronousQueue<>(),
            new ThreadFactory() {
                private AtomicInteger poolNumber = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable runnable) {
                    return new Thread(runnable, "cp_order_bet_thread_" + poolNumber.getAndIncrement());
                }
            }, new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws Exception {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(5);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MICROSECONDS, queue, new ThreadPoolExecutor.DiscardPolicy());

        poolExecutor.execute(() -> {
            System.out.println("running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        });

        poolExecutor.execute(() -> {
            System.out.println("running2");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        });

        poolExecutor.shutdown();

        while (!poolExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程还在执行");
        }

        System.out.println("main over");
    }

}
