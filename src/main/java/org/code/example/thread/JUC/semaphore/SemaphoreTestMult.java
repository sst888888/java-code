package org.code.example.thread.JUC.semaphore;

/**
 * @ClassName SemaphoreTestMult
 * @Description 多进程多处理
 * @Author Chaiphat
 * @Date 2020/4/4 21:11
 * @Version 1.0
 **/
public class SemaphoreTestMult {
    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            SemaphoreService service = new SemaphoreService();
            MyThread t = new MyThread("thread" + (i + 1), service);
            t.start();// 这里使用 t.run() 也可以运行，但是不是并发执行了
        }
    }

}
