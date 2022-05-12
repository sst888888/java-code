package queue.qpstest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName ConsumerTask
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/21 20:47
 * @Version 1.0
 **/
public class ConsumerTask implements Runnable{

    private BlockingQueue<String> queue;
    private TestCase testCase;
    private CountDownLatch startCountDownLatch;
    private CountDownLatch finishCountDownLatch;

    public ConsumerTask(CountDownLatch startCountDownLatch,
                        CountDownLatch finishCountDownLatch,
                        BlockingQueue<String> queue,
                        TestCase testCase) {
        this.startCountDownLatch = startCountDownLatch;
        this.finishCountDownLatch = finishCountDownLatch;
        this.queue = queue;
        this.testCase = testCase;
    }

    @Override
    public void run() {
        try {
            startCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = testCase.getElementCount() / testCase.getConsumerCount();

        if (testCase.getMode() != Mode.ProducerAndConsumerShareThread) {
            for (int i = 0; i < count; i++) {
                try {
                    queue.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        finishCountDownLatch.countDown();
    }
}
