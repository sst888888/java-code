package queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Producer
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/21 17:55
 * @Version 1.0
 **/
@Slf4j
public class Producer extends Worker{

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public Producer(String name, BlockingQueue<Integer> queue) {
        super(name, queue);
    }

    @Override
    public void run() {
        while (enable) {
            int value = atomicInteger.incrementAndGet();
            try {
                queue.put(value);
                log.info("size:{},put:{},enable:{}",queue.size(),value,enable);
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("{} quit",name);
    }
}
