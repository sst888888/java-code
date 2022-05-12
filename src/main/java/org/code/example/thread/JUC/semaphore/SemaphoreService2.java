package org.code.example.thread.JUC.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreService2
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/4 20:49
 * @Version 1.0
 **/
public class SemaphoreService2 extends SemaphoreService {
    private Semaphore semaphore = new Semaphore(6);// 6表示总共有6个通路

    @Override
    public void doSomething() {
        try {
            semaphore.acquire(2); // 2 表示进入此代码，就会消耗2个通路，2个通路从6个中扣除 每个线程消耗2个通路
            System.out.println(semaphore.getQueueLength());
            System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(2); // 释放占用的 2 个通路
        }
    }

    public int availablePermits() {    // 查看可用通路数
        return semaphore.availablePermits();
    }
}
