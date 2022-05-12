package org.code.example.thread.JUC.semaphore;

/**
 * @ClassName MyThread
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/4 18:30
 * @Version 1.0
 **/
public class MyThread extends Thread{
    private SemaphoreService service;

    public MyThread(String name, SemaphoreService service) {
        super();
        this.setName(name);
        this.service = service;
    }

    @Override
    public void run(){
        this.service.doSomething();
    }
}
