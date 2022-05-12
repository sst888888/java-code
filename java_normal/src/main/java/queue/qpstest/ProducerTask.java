package queue.qpstest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName ProducerTask
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/21 20:43
 * @Version 1.0
 **/
public class ProducerTask implements Runnable{

    private String name;
    private BlockingQueue<String> queue;
    private TestCase testCase;
    private CountDownLatch startCountDownLatch;
    private CountDownLatch finishCountDownLatch;

    public ProducerTask(CountDownLatch startCountDownLatch,
                        CountDownLatch finishCountDownLatch,
                        String name,
                        BlockingQueue<String> queue,
                        TestCase testCase) {
        this.startCountDownLatch = startCountDownLatch;
        this.finishCountDownLatch = finishCountDownLatch;
        this.name = name;
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

        int count = testCase.getElementCount() / testCase.getProducerCount();

        if (testCase.getMode() == Mode.ProducerAndConsumerShareThread) {
            for (int i = 0; i < count; i++) {
                try {
                    queue.put(name + i);
                    queue.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                try {
                    queue.put(name + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        finishCountDownLatch.countDown();
    }

}
