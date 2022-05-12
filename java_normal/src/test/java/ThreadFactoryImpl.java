import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName ThreadFactoryImpl
 * @Description 为线程的线程池起名字
 * @Author Chaiphat
 * @Date 2020/7/21 12:23
 * @Version 1.0
 **/
public class ThreadFactoryImpl implements ThreadFactory {

    private  AtomicLong threadIndex = new AtomicLong(0);
    private String threadNamePrefix;
    private boolean daemon;

    public ThreadFactoryImpl(final String threadNamePrefix) {
       this(threadNamePrefix,false);
    }

    public ThreadFactoryImpl(String threadNamePrefix, boolean daemon) {
        this.threadNamePrefix = threadNamePrefix;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, threadNamePrefix + this.threadIndex.incrementAndGet());
        thread.setDaemon(daemon);
        return thread;
    }
}
