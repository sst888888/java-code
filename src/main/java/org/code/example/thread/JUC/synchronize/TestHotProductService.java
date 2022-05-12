package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName TestHotProductService
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 14:42
 * @Version 1.0
 **/
public class TestHotProductService {

    public static void main(String[] args) {

        HotProductService productService = new HotProductService();
        ThreadA a = new ThreadA(productService);
        a.start();
        ThreadB b = new ThreadB(productService);
        b.start();
    }

}
