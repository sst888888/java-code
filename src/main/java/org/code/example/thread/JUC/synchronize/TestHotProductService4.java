package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName TestHotProductService4
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 15:10
 * @Version 1.0
 **/
public class TestHotProductService4 {

    public static void main(String[] args) {
        /**
         * new 2个不同的对象
         * 类方法加了类对象锁
         * methodA methodB不能同时执行 （同一个对象，在多个线程调用下，也不能同时执行）
         * 不同线程调用不同对象，也是顺序执行。
         * 如果methodB 不加锁 可以同时执行
         */
        HotProductService service = new HotProductService();
        HotProductService service2 = new HotProductService();
        ThreadA4 a = new ThreadA4(service);
        ThreadB4 b = new ThreadB4(service2);
        a.start();
        b.start();
    }

}
