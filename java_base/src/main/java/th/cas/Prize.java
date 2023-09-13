package th.cas;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * @author: cp
 * @date: 2023-09-11 20:39
 */
@Data
@Slf4j
public class Prize {

    /**
     * 一等奖：小米汽车
     */
    private String level;

    /**
     * 数量
     */
    private int count;

    public Prize(String level, int count) {
        this.level = level;
        this.count = count;
    }

    public static void main(String[] args) {
//        Prize prize = new Prize("小米汽车", 100);
//        AtomicInteger atomicInteger = new AtomicInteger();
//        IntStream.range(0, 300).forEach(
//            value -> new Thread(
//                    () -> {
//                        //①获得当前还剩多少号
//                        int count = prize.getCount();
//                        if (count > 0) {
//                            //②对剩余号源减1，并更新回奖池
//                            prize.setCount(count - 1);
//                            atomicInteger.incrementAndGet();
//                            log.info("当前线程:{},抢到了 {} 号", Thread.currentThread().getName(), count);
//                        }
//                    }
//            ).start()
//        );
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("中奖人数：{}", atomicInteger.get());

        //将我们的初始奖池封装到AtomicReference中
        AtomicReference<Prize> reference = new AtomicReference<>(new Prize("小米汽车", 100));
        AtomicInteger atomicInteger = new AtomicInteger(0);
        IntStream.range(0, 300).forEach(
            value -> new Thread(
                () -> {
                    //①获得当前还剩多少号的对象
                    final Prize prize = reference.get();
                    if (prize.getCount() > 0) {
                        //②对剩余号源进行减1
                        Prize prizeNew = new Prize(prize.getLevel(), reference.get().getCount() - 1);
                        //③将数据更新到奖池
                        if (reference.compareAndSet(prize, prizeNew)) {
                            log.info("当前线程:{},抢到了 {} 号", Thread.currentThread().getName(), prize.getCount());
                            atomicInteger.incrementAndGet();
                        }
                    }
                }
            ).start()
        );
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("中奖人数：{}", atomicInteger.get());
    }
}
