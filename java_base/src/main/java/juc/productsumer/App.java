package juc.productsumer;

/**
 * @ClassName App
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/18 21:11
 * @Version 1.0
 **/
public class App {

    public static void main(String[] args) {
        //共同的资源
        Movie m = new Movie();

        //多线程
        Player p = new Player(m);
        Watcher w = new Watcher(m);

        new Thread(p).start();
        new Thread(w).start();
    }

}
