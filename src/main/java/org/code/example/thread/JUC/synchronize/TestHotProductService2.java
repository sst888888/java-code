package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName TestHotProductService2
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 15:10
 * @Version 1.0
 **/
public class TestHotProductService2 {

    public static void main(String[] args) {
        HotProductService service = new HotProductService();
        ThreadA2 a = new ThreadA2(service);
        a.start();
        ThreadB2 b = new ThreadB2(service);
        b.start();
    }

}
