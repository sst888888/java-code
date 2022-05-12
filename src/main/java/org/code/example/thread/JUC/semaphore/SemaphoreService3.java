package org.code.example.thread.JUC.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreService3
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/4 21:07
 * @Version 1.0
 **/
public class SemaphoreService3 extends SemaphoreService {

    private Semaphore semaphore = new Semaphore(6, true);// 6表示总共有6个通路，true 表示公平

    @Override
    public void doSomething() {
        try {
            if (semaphore.tryAcquire(2, 3, TimeUnit.SECONDS)) { // 在 3秒 内 尝试获取 2 个通路

                System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr()
                        + "，当前是否有进程等待：" + semaphore.hasQueuedThreads() + "，等待进程数：" + semaphore.getQueueLength());
                semaphore.release(2); // 释放占用的 2 个通路
            } else {
                // 没有在3秒内获取到锁 就退出 第二次获取锁的线程，等其释放锁时，已经耗时4秒，后面的线程无法再获取锁
                System.out.println(Thread.currentThread().getName() + ":doSomething 没有获取到锁-准备退出-" + getFormatTimeStr());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int availablePermits() {
        return semaphore.availablePermits();
    }

}
