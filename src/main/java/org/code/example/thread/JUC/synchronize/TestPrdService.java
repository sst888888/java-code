package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName TestHotPrdService
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 15:27
 * @Version 1.0
 **/
public class TestPrdService {

    public static void main(String[] args) {
        PrdService prdService = new PrdService();
        /**
         * new的是同一个对象 prdService.lock 也是同一个对象 多线程下不能同时执行
         * 如果new 2个对象  可以并发执行
         */
        ThreadA3 a3 = new ThreadA3(prdService);
        ThreadB3 b3 = new ThreadB3(prdService);
        b3.start();
        a3.start();
    }

}
