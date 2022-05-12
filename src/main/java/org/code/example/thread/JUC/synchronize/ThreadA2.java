package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName ThreadA2
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 15:06
 * @Version 1.0
 **/
public class ThreadA2 extends Thread {
    private HotProductService productService;
    public ThreadA2(HotProductService hotProductService){
        super();
        this.productService = hotProductService;
    }

    @Override
    public void run(){
        super.run();
        productService.objectMethodA();
    }

}
