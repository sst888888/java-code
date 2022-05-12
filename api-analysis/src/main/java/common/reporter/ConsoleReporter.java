package common.reporter;

import common.bo.RequestInfo;
import common.bo.RequestStat;
import common.storage.MetricsStorage;
import common.storage.MockRedisMetricsStorage;
import common.storage.RedisMetricsStorage;
import common.util.Aggregator2;
import common.viewer.ConsoleViewer;
import common.viewer.EmailViewer;
import common.viewer.StatViewer;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ConsoleReporter2
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 20:33
 * @Version 1.0
 **/
public class ConsoleReporter extends ScheduledReporter{
    private MetricsStorage metricsStorage;
    private Aggregator2 aggregator2;
    private StatViewer viewer;
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator2 aggregator2, StatViewer viewer) {
      this.metricsStorage = metricsStorage;
      this.aggregator2 = aggregator2;
      this.viewer = viewer;
      this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public ConsoleReporter() {
        this(new MockRedisMetricsStorage(), new Aggregator2(), new ConsoleViewer());
        this.metricsStorage = new MockRedisMetricsStorage();
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport2(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                doStatAndReport(startTimeInMillis,endTimeInMillis);
            }
        }, 0L, periodInSeconds, TimeUnit.SECONDS);
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos =
                        metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                Map<String, RequestStat> requestStats = aggregator2.aggregate(requestInfos, durationInMillis);
                viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
            }
        }, 0L, periodInSeconds, TimeUnit.SECONDS);
    }

    public static void testEmailReporterTrimTimeFieldsToZeroOfNextDay() {
        MetricsStorage storage = new MockRedisMetricsStorage();
        Aggregator2 aggregator = new Aggregator2();
        EmailViewer emailViewer = new EmailViewer();
        EmailReporter emailReporter = new EmailReporter(storage, aggregator, emailViewer);
        Date date = emailReporter.trimTimeFieldsToZeroOfNextDay(new Date());
        System.out.println(date);
    }

    public static void main(String[] args) {
        testEmailReporterTrimTimeFieldsToZeroOfNextDay();
    }

}
