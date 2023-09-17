package th.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: cp
 * @date: 2023-09-17 15:33
 */
@Slf4j
public class PutThread2 extends Thread {
    private ConcurrentLinkedQueue<Integer> clq;

    public PutThread2(ConcurrentLinkedQueue<Integer> clq) {
        this.clq = clq;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                log.info("add {}", i);
                clq.add(i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}