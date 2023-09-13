package th.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: cp
 * @date: 2023-09-13 15:07
 * @link https://pdai.tech/md/java/thread/java-thread-x-juc-AtomicInteger.html
 */
@Slf4j
public class AtomicTester {

    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(1, 0);

    public static void main(String[] args){
        first().start();
        second().start();
    }

    private static Thread first() {
        return new Thread(() -> {
            log.info("操作线程{}, 初始值 a = {}", Thread.currentThread(), atomicStampedRef.getReference());
            int stamp = atomicStampedRef.getStamp(); //获取当前标识别
            try {
                Thread.sleep(1000); //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1,2,stamp,stamp +1);  //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            log.info("操作线程{}, CAS操作结果: {}", Thread.currentThread(), isCASSuccess);
        },"主操作线程");
    }

    private static Thread second() {
        return new Thread(() -> {
            Thread.yield(); // 确保thread-first 优先执行
            atomicStampedRef.compareAndSet(1,2,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            log.info("操作线程{},【increment】, 值 = {}", Thread.currentThread(), atomicStampedRef.getReference());
            atomicStampedRef.compareAndSet(2,1,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            log.info("操作线程{},【decrement】, 值 = {}", Thread.currentThread(), atomicStampedRef.getReference());
        },"干扰线程");
    }

}
