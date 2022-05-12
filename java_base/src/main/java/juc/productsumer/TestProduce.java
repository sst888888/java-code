package juc.productsumer;

/**
 * @ClassName TestProduce
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/18 21:17
 * @Version 1.0
 **/
public class TestProduce {

    public static void main(String[] args) {
        SyncStack sStack = new SyncStack();
        Shengchan sc = new Shengchan(sStack);
        Xiaofei xf = new Xiaofei(sStack);
        sc.start();
        xf.start();
    }

}
