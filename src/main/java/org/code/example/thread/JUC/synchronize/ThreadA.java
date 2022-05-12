package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName ThreadA
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 14:38
 * @Version 1.0
 **/
public class ThreadA extends Thread{
    private HotProductService hotProductService;

    public ThreadA(HotProductService objectService){
        super();
        this.hotProductService = objectService;
    }

    @Override
    public void run(){
        super.run();
        hotProductService.serviceMethodA();
    }

}
