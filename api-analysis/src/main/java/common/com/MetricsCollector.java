package common.com;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import common.bo.RequestInfo;
import common.storage.MetricsStorage;
import common.storage.MockRedisMetricsStorage;
import org.junit.platform.commons.util.StringUtils;

import java.util.concurrent.Executors;

/**
 * @ClassName MetricsCollector
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 21:24
 * @Version 1.0
 **/
public class MetricsCollector {

    private static final int DEFAULT_STORAGE_THREAD_POOL_SIZE = 20;

    private MetricsStorage metricsStorage;//基于接口而非实现编程
    private EventBus eventBus;

    //依赖注入
//    public MetricsCollector(MetricsStorage metricsStorage) {
//        this.metricsStorage = metricsStorage;
//    }

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public MetricsCollector() {
//        this(new MockRedisMetricsStorage());
        this.metricsStorage = new MockRedisMetricsStorage();
    }

    public MetricsCollector(MetricsStorage metricsStorage) {
        this(metricsStorage, DEFAULT_STORAGE_THREAD_POOL_SIZE);
    }

    public MetricsCollector(MetricsStorage metricsStorage, int threadNumToSaveData) {
        this.metricsStorage = metricsStorage;
        this.eventBus = new AsyncEventBus(Executors.newFixedThreadPool(threadNumToSaveData));
        this.eventBus.register(new EventListener());
    }

    // 用一个函数代替了最小原型中的两个函数
    // 采集和存储要异步来执行
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
//        metricsStorage.saveRequestInfo(requestInfo);
        eventBus.post(requestInfo);
    }

    public class EventListener {
        @Subscribe
        public void saveRequestInfo(RequestInfo requestInfo) {
            metricsStorage.saveRequestInfo(requestInfo);
        }
    }

}
