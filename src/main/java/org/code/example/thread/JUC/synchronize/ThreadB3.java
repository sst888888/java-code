package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName ThreadB3
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 15:06
 * @Version 1.0
 **/
public class ThreadB3 extends Thread {
    private PrdService service;
    public ThreadB3(PrdService prdService){
        super();
        this.service = prdService;
    }

    @Override
    public void run(){
        super.run();
        service.setUsernamePassword("b","bb");
    }

}
