package th.collection;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: cp
 * @date: 2023-09-17 15:33
 */
public class PutThread extends Thread {
    private CopyOnWriteArrayList<Integer> copyOnWriteArrayList;

    public PutThread(CopyOnWriteArrayList<Integer> copyOnWriteArrayList) {
        this.copyOnWriteArrayList = copyOnWriteArrayList;
    }

    @Override
    public void run() {
        try {
            for (int i = 100; i < 110; i++) {
                copyOnWriteArrayList.add(i);
                Thread.sleep(55);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}