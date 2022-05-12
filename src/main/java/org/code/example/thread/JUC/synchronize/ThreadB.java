package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName ThreadB
 * @Description TODO
 * @Author Chaiphat
 * @Date 2020/4/29 14:40
 * @Version 1.0
 **/
public class ThreadB extends Thread {
    private HotProductService hotProductService;

    public ThreadB(HotProductService objectService){
        super();
        this.hotProductService = objectService;
    }

    @Override
    public void run(){
        super.run();
        hotProductService.serviceMethodB();
    }
}
