package workerThread;

import java.util.Random;

/**
 * @ClassName Worker
 * @Description 机器人
 * @Author Chaiphat
 * @Date 2020/8/3 10:23
 * @Version 1.0
 **/
public class Worker extends Thread{

    private static final Random random = new Random(System.currentTimeMillis());
    private final PackageChannel channel;

    public Worker(String name,PackageChannel channel) {
        super(name);
        this.channel = channel;
    }

    @Override public void run() {
        while (true) {
            channel.take().execute();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
