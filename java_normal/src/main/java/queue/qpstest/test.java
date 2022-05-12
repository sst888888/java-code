package queue.qpstest;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName test
 * @Description
 * 阻塞队列中，无界队列可以选择LinkedBlockingQueue，
 * 有界队列可以选择ArrayBlockingQueue，后者还有公平参数可以开启公平特性
 * @Author Chaiphat
 * @Date 2020/7/21 20:48
 * @Version 1.0
 **/
@Slf4j
public class test {

    private static int element_count = 100000;

    public static void main(String[] args) throws InterruptedException {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(element_count, Mode.ConcurrentProducerAndConsumer, 1, 1));
        testCases.add(new TestCase(element_count, Mode.ConcurrentProducerAndConsumer, 10, 10));
        testCases.add(new TestCase(element_count, Mode.ConcurrentProducerAndConsumer, 100, 100));
        testCases.add(new TestCase(element_count, Mode.ConcurrentProducerAndConsumer, 1000, 1000));
        testCases.add(new TestCase(element_count, Mode.ConcurrentProducerAndConsumer, Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors()));

        testCases.add(new TestCase(element_count, Mode.ConcurrentProducerAndConsumer, 1, 100));
        testCases.add(new TestCase(element_count, Mode.ConcurrentProducerAndConsumer, 100, 1));

        testCases.add(new TestCase(element_count, Mode.ProducerAndConsumerShareThread, 1, 0));
        testCases.add(new TestCase(element_count, Mode.ProducerAndConsumerShareThread, 10, 0));
        testCases.add(new TestCase(element_count, Mode.ProducerAndConsumerShareThread, 100, 0));
        testCases.add(new TestCase(element_count, Mode.ProducerAndConsumerShareThread, 1000, 0));
        testCases.add(new TestCase(element_count, Mode.ProducerAndConsumerShareThread, Runtime.getRuntime().availableProcessors(), 0));

        testCases.add(new TestCase(element_count, Mode.ProducerAndThenConsumer, 1, 1));
        testCases.add(new TestCase(element_count, Mode.ProducerAndThenConsumer, 10, 10));
        testCases.add(new TestCase(element_count, Mode.ProducerAndThenConsumer, 100, 100));
        testCases.add(new TestCase(element_count, Mode.ProducerAndThenConsumer, 1000, 1000));
        testCases.add(new TestCase(element_count, Mode.ProducerAndThenConsumer, Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors()));


        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        for (TestCase testCase : testCases) {
            System.gc();
            benchmark(linkedBlockingQueue, testCase);
        }

        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
        for (TestCase testCase : testCases) {
            System.gc();
            benchmark(linkedTransferQueue, testCase);
        }

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(element_count);
        for (TestCase testCase : testCases) {
            System.gc();
            benchmark(arrayBlockingQueue, testCase);
        }
    }

    private static void benchmark(BlockingQueue<String> queue, TestCase testCase) throws InterruptedException {

        long begin = System.currentTimeMillis();
        log.info("\r\n==========================\r\nBegin benchmark Queue:[{}], case:{}", queue.getClass().getSimpleName(),
                testCase.toString());
        CountDownLatch startCountDownLatch = new CountDownLatch(1);

        if (testCase.getMode() == Mode.ProducerAndConsumerShareThread) {
            CountDownLatch finishCountDownLatch = new CountDownLatch(testCase.getProducerCount());
            for (int i = 0; i < testCase.getProducerCount(); i++) {
                new Thread(new ProducerTask(
                        startCountDownLatch,
                        finishCountDownLatch,
                        String.format("Thread_%d_", i),
                        queue,
                        testCase)).start();
            }
            startCountDownLatch.countDown();
            finishCountDownLatch.await();

        } else if (testCase.getMode() == Mode.ConcurrentProducerAndConsumer) {
            CountDownLatch finishCountDownLatch = new CountDownLatch(testCase.getProducerCount() + testCase.getConsumerCount());
            for (int i = 0; i < testCase.getProducerCount(); i++) {
                new Thread(new ProducerTask(
                        startCountDownLatch,
                        finishCountDownLatch,
                        String.format("Thread_%d_", i),
                        queue,
                        testCase)).start();
            }
            for (int i = 0; i < testCase.getConsumerCount(); i++) {
                new Thread(new ConsumerTask(
                        startCountDownLatch,
                        finishCountDownLatch,
                        queue,
                        testCase)).start();
            }
            startCountDownLatch.countDown();
            finishCountDownLatch.await();
        } else if (testCase.getMode() == Mode.ProducerAndThenConsumer) {
            CountDownLatch finishCountDownLatch = new CountDownLatch(testCase.getProducerCount());
            for (int i = 0; i < testCase.getProducerCount(); i++) {
                new Thread(new ProducerTask(
                        startCountDownLatch,
                        finishCountDownLatch,
                        String.format("Thread_%d_", i),
                        queue,
                        testCase)).start();
            }
            startCountDownLatch.countDown();
            finishCountDownLatch.await();

            startCountDownLatch = new CountDownLatch(1);
            finishCountDownLatch = new CountDownLatch(testCase.getConsumerCount());
            for (int i = 0; i < testCase.getConsumerCount(); i++) {
                new Thread(new ConsumerTask(
                        startCountDownLatch,
                        finishCountDownLatch,
                        queue,
                        testCase)).start();
            }
            startCountDownLatch.countDown();
            finishCountDownLatch.await();
        }

        long finish = System.currentTimeMillis();
        log.info("Finish benchmark Queue:[{}], case:{}, QPS:{}\r\n==========================\n", queue.getClass().getSimpleName(),
                testCase.toString(),
                (long) element_count * 1000 / (finish - begin));
    }
}
