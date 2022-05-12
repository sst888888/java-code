package org.code.example.thread.JUC.semaphore;

/**
 * @ClassName SemaphoreTest
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/4 19:47
 * @Version 1.0
 **/
public class SemaphoreTest {
    public static void main(String[] args) {
        SemaphoreService service = new SemaphoreService();
        for(int i = 0; i < 10; i++){
            MyThread t = new MyThread("thread" + (i+1),service);
            t.start();
        }
    }
}
