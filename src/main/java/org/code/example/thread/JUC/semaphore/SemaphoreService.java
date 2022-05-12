package org.code.example.thread.JUC.semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreService
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/4 19:30
 * @Version 1.0
 **/
public class SemaphoreService {
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 构造方法传入的数字是多少，则同一时刻，只运行多少个进程同时运行指定代码
     */
    private Semaphore semaphore = new Semaphore(2);
    public void doSomething(){
        /**
         * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许指定个数的线程进入，
         * 因为semaphore的构造方法是2，则同一时刻只允许2个线程进入，其他线程等待。
         * */
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static String getFormatTimeStr(){
        return sf.format(new Date());
    }
}
