package th.collection;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: cp
 * @date: 2023-09-17 19:25
 */
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<>();
        PutThread2 p1 = new PutThread2(clq);
        GetThread g1 = new GetThread(clq);

        p1.start();
        g1.start();

    }

}
