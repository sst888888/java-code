import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

/**
 * @ClassName ForkJoinPoolBenchmark
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/20 21:39
 * @Version 1.0
 **/
@Slf4j
public class ForkJoinPoolBenchmark {

    @Test
    public void test() throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong();
        StopWatch stopWatch = new StopWatch();
        ExecutorService normal = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorService forkJoin = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors());

        stopWatch.start("normal");
        LongStream.rangeClosed(1, 10000000).forEach(i -> normal.submit(atomicLong::incrementAndGet));
        normal.shutdown();
        normal.awaitTermination(1, TimeUnit.HOURS);
        stopWatch.stop();

        long r = atomicLong.get();
        stopWatch.start("forkJoin");
        LongStream.rangeClosed(1, 10000000).forEach(i -> forkJoin.submit(atomicLong::incrementAndGet));
        forkJoin.shutdown();
        forkJoin.awaitTermination(1, TimeUnit.HOURS);
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());
        log.info("result:{},{}", r, atomicLong.get());

    }
}
