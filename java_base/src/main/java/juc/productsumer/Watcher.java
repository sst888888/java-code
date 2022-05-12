package juc.productsumer;

/**
 * @ClassName Watcher
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/18 21:10
 * @Version 1.0
 **/
public class Watcher implements Runnable {

    private Movie m;

    public Watcher(Movie m) {
        super();
        this.m = m;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            m.watch();
        }
    }
}
