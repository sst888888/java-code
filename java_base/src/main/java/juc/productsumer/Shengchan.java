package juc.productsumer;

/**
 * @ClassName Shengchan
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/18 21:15
 * @Version 1.0
 **/
public class Shengchan extends Thread{
    SyncStack ss = null;

    public Shengchan(SyncStack ss) {
        this.ss=ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("造馒头："+i);
            Mantou m = new Mantou(i);
            ss.push(m);
        }
    }

}
