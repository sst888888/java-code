package juc.productsumer;

/**
 * @ClassName Player
 * @Description 生产者
 * @Author Chaiphat
 * @Date 2020/8/18 21:09
 * @Version 1.0
 **/
public class Player implements Runnable {
    private Movie m;

    public Player(Movie m) {
        super();
        this.m = m;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                m.play("左青龙");
            } else {
                m.play("右白虎");
            }
        }
    }

}
