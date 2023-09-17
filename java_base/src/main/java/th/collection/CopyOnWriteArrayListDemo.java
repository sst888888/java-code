package th.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: cp
 * @date: 2023-09-17 15:35
 */
@Slf4j
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> cowal = new CopyOnWriteArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            cowal.add(i);
        }
        PutThread p1 = new PutThread(cowal);
        p1.start();
        Iterator<Integer> iterator = cowal.iterator();
        while (iterator.hasNext()) {
            log.info(iterator.next() + " ");
        }
        System.out.println();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        iterator = cowal.iterator();
        while (iterator.hasNext()) {
           log.info(iterator.next() + " ");
        }
    }
}
