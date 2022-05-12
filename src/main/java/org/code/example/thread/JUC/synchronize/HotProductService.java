package org.code.example.thread.JUC.synchronize;

import java.time.Instant;

/**
 * @ClassName HotProductService
 * @Description
 * @Date 2020/4/29 14:32
 * @Version 1.0
 **/
public class HotProductService {

    /**
     * synchronized (this)使用的对象监视器是一个，
     * 当一个线程访问HotProductService的一个synchronized (this)同步代码块时，
     * 其他线程对同一个HotProductService中其它的synchronized (this)同步代码块的访问将是堵塞的，
     * 实现了代码顺序的同步执行
    */
    public void serviceMethodA(){
        synchronized (this){
            System.out.println("HotProductService A begin time = " + Instant.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("HotProductService A end time = " + Instant.now());
        }
    }

    public void serviceMethodB(){
        synchronized (this){
            System.out.println("HotProductService B begin time = " + Instant.now());
            System.out.println("HotProductService B end time = " + Instant.now());
        }
    }

    /**
     * 验证只能同时被一个线程所访问synchronized代码块。其他线程遇到synchronized代码块将会堵塞。
     * 一个线程在执行objectMethodB时，其他线程不能再执行objectMethodA
     */

    public synchronized void objectMethodA(){
        System.out.println("HotProductService synchronized thread name " + Thread.currentThread().getName());
        System.out.println("HotProductService run objectMethodA");
    }

    public void objectMethodB(){
        synchronized (this){
            for(int i = 1; i < 5; i++){
                System.out.println("HotProductService synchronized thread name " + Thread.currentThread().getName() + "--> i = " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 锁住的是类
     */
    public void methodA(){
        synchronized (HotProductService.class){
            System.out.println("HotProductService methodA begin name " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("HotProductService methodA end name " + Thread.currentThread().getName());
        }
    }


    public void methodB(){
        synchronized (HotProductService.class){
            System.out.println("HotProductService methodB begin name " + Thread.currentThread().getName());
            System.out.println("HotProductService methodB end name " + Thread.currentThread().getName());
        }
    }
}
