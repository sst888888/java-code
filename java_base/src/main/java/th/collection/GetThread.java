package th.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: cp
 * @date: 2023-09-17 19:24
 */
@Slf4j
public class GetThread extends Thread {
    private ConcurrentLinkedQueue<Integer> clq;

    public GetThread(ConcurrentLinkedQueue<Integer> clq) {
        this.clq = clq;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                log.info("poll {}", clq.poll());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}