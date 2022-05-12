package org.code.example.thread.JUC.semaphore;

/**
 * @ClassName SemaphoreTest
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/4 19:47
 * @Version 1.0
 **/
public class SemaphoreTest4 {
    public static void main(String[] args) {
        SemaphoreService4 service = new SemaphoreService4();
        for(int i = 0; i < 10; i++){
            MyThread t = new MyThread("thread" + (i+1),service);
            t.start();
        }
    }


    static class MyThread extends Thread{
        private SemaphoreService4 service;

        public MyThread(String name, SemaphoreService4 service) {
            super();
            this.setName(name);
            this.service = service;
        }

        @Override
        public void run(){
            this.service.doSomething();
        }
    }
}
