import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @ClassName ThreadPoolExceptionTest
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/20 21:53
 * @Version 1.0
 **/
@Slf4j
public class ThreadPoolExceptionTest {

    @Before
    public void setDefaultUncaughtExceptionHandler(){
        Thread.setDefaultUncaughtExceptionHandler((Thread t,Throwable e) -> log.warn("Exception in thread{}",t,e));
    }

    /**
     * 线程池里的任务出了未处理异常，最终这个异常算是未处理异常，可以由未处理异常进行处理
     * 这个线程会终止，而不是传说的线程池会捕获异常
     * 线程池只能重新创建新的异常来填补空白，这是有代价的
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        String prefix = "test";
        ExecutorService threadPool = Executors.newFixedThreadPool(1, new ThreadFactoryImpl(prefix));
        IntStream.rangeClosed(1,10).forEach(i -> threadPool.execute(()->{
            if (i == 5) throw new RuntimeException("error");
            log.info("I'm done : {}", i);
            if (i < 5) Assert.assertEquals(prefix + "1", Thread.currentThread().getName());
            else Assert.assertEquals(prefix + "2", Thread.currentThread().getName());
        }));
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    /**
     * 异常会在Future.get()的时候拿到，因此线程也不会死亡。
     * @throws InterruptedException
     */
    @Test
    public void testFuture() throws InterruptedException {
        String prefix = "test";
        ExecutorService threadPool = Executors.newFixedThreadPool(1, new ThreadFactoryImpl(prefix));
        ArrayList<Future> futures = new ArrayList<>();

        IntStream.rangeClosed(1,10).forEach(i -> futures.add(threadPool.submit(()->{
            if (i == 5) throw new RuntimeException("error");
            log.info("I'm done : {}", i);
        })));

        for (Iterator<Future> iterator = futures.iterator(); iterator.hasNext(); ) {
            Future next =  iterator.next();
            try {
                next.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.warn("future ExecutionException",e);
            } catch (ExecutionException e) {
                e.printStackTrace();
                log.warn("future ExecutionException",e);
            }
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    @Test
    public void test2() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        ExecutorService threadPool1 = Executors.newFixedThreadPool(1);
        stopWatch.start("execute");
        IntStream.rangeClosed(1, 100000).forEach(i->threadPool1.execute(()->{
            throw new RuntimeException("error");
        }));
        threadPool1.shutdown();
        threadPool1.awaitTermination(1, TimeUnit.HOURS);
        stopWatch.stop();

        ExecutorService threadPool2 = Executors.newFixedThreadPool(1);
        stopWatch.start("submit");
        IntStream.rangeClosed(1, 100000).forEach(i->threadPool2.submit(()->{
            throw new RuntimeException("error");
        }));
        threadPool2.shutdown();
        threadPool2.awaitTermination(1, TimeUnit.HOURS);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }
    

}
