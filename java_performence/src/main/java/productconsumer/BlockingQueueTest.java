package productconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName BlockingQueueTest
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/3 11:46
 * @Version 1.0
 **/
@Slf4j
public class BlockingQueueTest {
    private int maxInventory = 10;
    private BlockingQueue<String> product = new LinkedBlockingQueue<>(maxInventory);

    /**
     * 新增商品库存
     * @param e
     */
    public void produce(String e) {
        try {
            product.put(e);
            log.info("放入一个商品库存，总库存为：" + product.size());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public String consume() {
        String result = null;
        try {
            result = product.take();
            log.info("消费一个商品，总库存为：" + product.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 生产者
     * @author admin
     *
     */
    private class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                produce("商品" + i);
            }
        }
    }

    /**
     * 消费者
     * @author admin
     *
     */
    private class Customer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                consume();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueueTest lc = new BlockingQueueTest();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
    }
}
