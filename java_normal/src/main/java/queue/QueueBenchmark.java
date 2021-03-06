package queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * @ClassName QueueBenchmark
 * @Description 非阻塞队列的测试
 * @Author Chaiphat
 * @Date 2020/7/21 19:43
 * @Version 1.0
 **/
@Slf4j
public class QueueBenchmark {

    int taskCount = 2000000;
    int threadCount = 10;

    @Test
    public void test() {
        List<Queue<Integer>> queues = getQueues();
        benchmark("add", queues, taskCount, threadCount);
        benchmark("poll", queues, taskCount, threadCount);
        benchmark("offer", queues, taskCount, threadCount);
        benchmark("size", queues, taskCount, threadCount);
        benchmark("remove", queues, taskCount, threadCount);
    }

    private List<Queue<Integer>> getQueues() {
        return Arrays.asList(new ConcurrentLinkedQueue<>(),
                new LinkedBlockingQueue<>(),
                new ArrayBlockingQueue<>(taskCount, false),
                new LinkedTransferQueue<>(),
                new PriorityBlockingQueue<>(),
                new LinkedList<>());
    }

    private void benchmark(String operation, List<Queue<Integer>> queues, int taskCount, int threadCount) {
        StopWatch stopWatch = new StopWatch();
        queues.forEach(queue -> {
            stopWatch.start(queue.getClass().getSimpleName() + "-" + operation);
            try {
                tasks(queue, taskCount, threadCount, operation);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stopWatch.stop();
            log.info("queue:{},operation:{},size:{},qps:{}", queue.getClass().getSimpleName(), operation, queue.size(),
                    (long) taskCount * 1000 / stopWatch.getLastTaskTimeMillis());
        });
        log.info(stopWatch.prettyPrint());
    }


    private void tasks(Queue<Integer> queue, int taskCount, int threadCount, String operation) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> {
            IntConsumer opt = task(queue, operation);
            if (queue instanceof LinkedList) {
                synchronized (queue) {
                    opt.accept(i);
                }
            } else {
                opt.accept(i);
            }
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    }

    private IntConsumer task(Queue<Integer> queue, String name) {
        if (name.equals("add")) return queue::add;
        if (name.equals("offer")) return queue::offer;
        if (name.equals("poll")) return i -> queue.poll();
        if (name.equals("remove")) return i -> queue.remove();
        if (name.equals("size")) return i -> queue.size();
        return i -> {
        };
    }

}
