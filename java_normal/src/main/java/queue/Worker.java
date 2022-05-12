package queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

/**
 * @ClassName Worker
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/21 17:48
 * @Version 1.0
 **/
@Slf4j
public abstract class Worker implements Runnable {

    // 通过一个开关来控制生产者消费者的执行
    protected volatile boolean enable = true;
    protected String name;
    protected BlockingQueue<Integer> queue;

    public Worker(String name, BlockingQueue<Integer> queue) {
        this.name = name;
        this.queue = queue;
    }

    public void stop() {
        this.enable = false;
        log.info("Stop:{}", name);
    }

}
