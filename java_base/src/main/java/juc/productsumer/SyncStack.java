package juc.productsumer;

/**
 * @ClassName SyncStack
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/18 21:13
 * @Version 1.0
 **/
public class SyncStack {

    int index = 0;
    Mantou[] ms = new Mantou[10];

    public synchronized void push(Mantou m) {
        while (index == ms.length) {
            try {
                this.wait();
                //wait后，线程会将持有的锁释放。sleep是即使睡着也持有互斥锁。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify(); //唤醒在当前对象等待池中等待的第一个线程。notifyAll叫醒所有在当前对象等待池中等待的所有线程。
        //如果不唤醒的话。以后这两个线程都会进入等待线程，没有人唤醒。
        ms[index] = m;
        index++;
        System.out.println(index);
    }

    public synchronized Mantou pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        index--;
        return ms[index];
    }

}
