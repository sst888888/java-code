package org.code.example.thread;

import jdk.internal.org.objectweb.asm.util.Printer;

/**
 * @ClassName JoinDemo
 * @Description
 * @Author Chaiphat
 * @Date 2020/2/18 16:13
 * @Version 1.0
 **/
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            System.out.println("running");
            try {
                Thread.sleep(3000);
                System.out.println("thread1 end");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        t1.setName("test");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("running2");
                try {
                    Thread.sleep(2000);
                    System.out.println("thread2 end");
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("end.........");

    }



}
