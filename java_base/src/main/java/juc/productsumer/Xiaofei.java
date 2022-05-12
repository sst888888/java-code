package juc.productsumer;

/**
 * @ClassName Xiaofei
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/18 21:16
 * @Version 1.0
 **/
public class Xiaofei extends Thread {

    SyncStack ss = null;

    public Xiaofei(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Mantou m = ss.pop();
            System.out.println("吃馒头：" + i);
        }
    }

}
