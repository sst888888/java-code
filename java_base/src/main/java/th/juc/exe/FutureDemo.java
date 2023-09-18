package th.juc.exe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: cp
 * @date: 2023-09-18 14:53
 */
@Slf4j
public class FutureDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit((Callable<Object>) () -> {
            Long start = System.currentTimeMillis();
            while (true) {
                Long current = System.currentTimeMillis();
                if ((current - start) > 10000) {
                    return 1;
                }
            }
        });

        try {
            Integer result = (Integer)future.get();
            log.info("result = {}", result);
            executorService.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
