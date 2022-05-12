package productconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockConditionTest
 * @Description Lock 中 Condition 的 await/signal/signalAll 实现生产者消费者
 * @Author Chaiphat
 * @Date 2020/8/3 11:21
 * @Version 1.0
 **/
@Slf4j
public class LockConditionTest {

    private LinkedList<String> product = new LinkedList<String>();
    // 实时库存
    private AtomicInteger inventory = new AtomicInteger(0);
    // 最大库存
    private int maxInventory = 10;
    private Lock consumerLock = new ReentrantLock();
    private Lock productLock = new ReentrantLock();

    private Condition notEmptyCondition = consumerLock.newCondition();
    private Condition notFullCondition = productLock.newCondition();

    /**
     * 新增商品库存
     * @param e
     */
    public void produce(String e) {
        productLock.lock();
        try {
            while (inventory.get() == maxInventory) {
                notFullCondition.await();
            }

            product.add(e);
            log.info("放入一个商品库存，总库存为：" + inventory.incrementAndGet());

            if(inventory.get() < maxInventory) {
                notFullCondition.signalAll();
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            productLock.unlock();
        }

        if(inventory.get() > 0) {
            try {
                consumerLock.lockInterruptibly();
                notEmptyCondition.signalAll();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } finally {
                consumerLock.unlock();
            }
        }
    }

    /**
     * 消费商品
     * @return
     */
    public String consume() {
        String result = null;
        consumerLock.lock();
        try {
            while (inventory.get() == 0) {
                notEmptyCondition.await();
            }

            // 从尾部消费
            result = product.removeLast();
            log.info("消费一个商品，总库存为：" + inventory.decrementAndGet());

            if(inventory.get() > 0) {
                notEmptyCondition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumerLock.unlock();
        }

        if(inventory.get() < maxInventory) {
            try {
                productLock.lockInterruptibly();
                notFullCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                productLock.unlock();
            }
        }

        return result;
    }


    private class Producer implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 20; i++) {
                produce("商品" + i);
            }
        }
    }

    private class Customer implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 20; i++) {
                consume();
            }
        }
    }

    public static void main(String[] args) {
        LockConditionTest lc = new LockConditionTest();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
    }

}
