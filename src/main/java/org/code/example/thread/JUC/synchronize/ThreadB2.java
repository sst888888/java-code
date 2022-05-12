package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName ThreadB2
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 15:09
 * @Version 1.0
 **/
public class ThreadB2 extends Thread{

    private HotProductService productService;
    public ThreadB2(HotProductService hotProductService){
        super();
        this.productService = hotProductService;
    }

    @Override
    public void run(){
        super.run();
        productService.objectMethodB();
    }

}
