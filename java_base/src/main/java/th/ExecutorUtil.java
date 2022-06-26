package th;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

@Slf4j
public class ExecutorUtil {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 500;
    private static final long KEEP_ALIVE_TIME = 3000;

    private static final int MAXIMUM_POOL_FOR_NAME_SIZE = 100;

    private static final String THREAD_NAME = "csc-pool";

    public static final String QUEUE_ALLOCATOR_THREAD_NAME = "csc-queue-allocator-pool";

    public static final String LOG_STR = "线程池初始化完毕! threadName: {}, core_pool_size: {}, maximum_pool_size: {}, keep_alive_time: {}";

    /**
     * 线程池名：不存在相同的线程名，则默认为创建新线程池
     */
    private static Map<String, ExecutorService> threadMaps = Maps.newConcurrentMap();

    /**
     * 此处默认不传线程名时，采用的系统全局统一线程池
     * @return
     */
    public static ExecutorService getInstance() {
        return getInstance(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, THREAD_NAME);
    }

    /**
     * 此次输入自定义线程名时，则单独创建分配线程池。此为独立线程池
     * @param threadName
     * @return
     */
    public static ExecutorService getInstance(String threadName) {
        ExecutorService pool = threadMaps.get(threadName);
        if (Objects.isNull(pool)) {
            synchronized (Map.class) {
                pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_FOR_NAME_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), getNewThreadFactory(threadName));
//                pool = new ExecutorServiceDecorator(pool);
                threadMaps.put(threadName, pool);
                log.info(LOG_STR, threadName, CORE_POOL_SIZE, MAXIMUM_POOL_FOR_NAME_SIZE, KEEP_ALIVE_TIME);
            }
        }
        return pool;
    }

    public static ExecutorService getInstance(int corePoolSize, int maximumPoolSize) {
        return getInstance(corePoolSize, maximumPoolSize, THREAD_NAME);
    }

    public static ExecutorService getInstance(int corePoolSize, int maximumPoolSize, String threadName) {
        ExecutorService pool = threadMaps.get(threadName);
        if (Objects.isNull(pool)) {
            synchronized (Map.class) {
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), getNewThreadFactory(threadName));
//                pool = new ExecutorServiceDecorator(pool);
                threadMaps.put(threadName, pool);
                log.info(LOG_STR, threadName, corePoolSize, maximumPoolSize, KEEP_ALIVE_TIME);
            }
        }
        return pool;
    }

    public static ExecutorService getInstance(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit) {
        return getInstance(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, THREAD_NAME);
    }

    public static ExecutorService getInstance(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit, String threadName) {
        ExecutorService pool = threadMaps.get(threadName);
        if (Objects.isNull(pool)) {
            synchronized (Map.class) {
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, new SynchronousQueue<>(), getNewThreadFactory(threadName));
//                pool = new ExecutorServiceDecorator(pool);
                threadMaps.put(threadName, pool);
                log.info(LOG_STR, threadName, corePoolSize, maximumPoolSize, keepAliveTime);
            }
        }
        return pool;
    }

    public static ThreadFactory getNewThreadFactory(String threadName) {
        return r -> new Thread(r, StringUtils.join(threadName, "_pool_", r.hashCode()));
    }
}
