package queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Consumer
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/21 17:59
 * @Version 1.0
 **/
@Slf4j
public class Consumer extends Worker {
    private static AtomicInteger totalConsumedAfterShutdown = new AtomicInteger();

    public Consumer(String name, BlockingQueue<Integer> queue) {
        super(name, queue);
    }

    public static int totalConsumedAfterShutdown() {
        return totalConsumedAfterShutdown.get();
    }


    @Override
    public void run() {
        while (enable || queue.size() > 0) {
            try {
//                Integer item = queue.take();
//                log.info("size:{}, got:{}, enable:{}", queue.size(), item, enable);
//                if (!enable) {
//                    // 计数器用来统计开关关闭之后，消费者还能消费多少数据。
//                    totalConsumedAfterShutdown.incrementAndGet();
//                }
                Integer item = queue.poll(1, TimeUnit.SECONDS);
                log.info("size:{}, got:{}, enable:{}", queue.size(), item, enable);
                if (!enable && item != null) {
                    totalConsumedAfterShutdown.incrementAndGet();
                }
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        log.info("{} quit", name);
    }
}
