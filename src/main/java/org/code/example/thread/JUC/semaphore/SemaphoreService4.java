package org.code.example.thread.JUC.semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreService4
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/4 21:18
 * @Version 1.0
 **/
public class SemaphoreService4{

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private Semaphore semaphore = new Semaphore(2);// 同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码

    public void doSomething() {
        try {
            /**
             * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
             * 因为semaphore的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
             * */
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " acquire success " + getFormatTimeStr());
            doSomethingMain(); // 将主要处理部分封装成一个方法
//            execute();
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 同步方法
    private static synchronized void doSomethingMain() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr());
    }

    // 非同步方法
    private static void execute() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr());
    }

    public static String getFormatTimeStr() {
        return sf.format(new Date());
    }


}
