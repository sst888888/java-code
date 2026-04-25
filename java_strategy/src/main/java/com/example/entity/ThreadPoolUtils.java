package com.example.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadPoolUtils {


    public static ThreadPoolExecutor genetePool(int core, int max) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(core, max, 600, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("BadacoinTask" + "-" + threadCount.getAndIncrement());
                return thread;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                r.run();
                log.info("触发主线程执行");
            }
        });
        return threadPoolExecutor;
    }
}