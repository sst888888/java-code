package common.reporter;

import com.google.common.annotations.VisibleForTesting;
import common.bo.RequestInfo;
import common.bo.RequestStat;
import common.storage.MetricsStorage;
import common.storage.RedisMetricsStorage;
import common.util.Aggregator2;
import common.viewer.EmailViewer;
import common.viewer.StatViewer;

import java.util.*;

/**
 * @ClassName EmailReporter2
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 20:38
 * @Version 1.0
 **/
public class EmailReporter extends ScheduledReporter{

    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator2 aggregator2;
    private StatViewer viewer;

    public EmailReporter(MetricsStorage metricsStorage, Aggregator2 aggregator2, StatViewer viewer) {
        super(metricsStorage,aggregator2,viewer);
    }

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public EmailReporter(List<String> emailToAddresses) {
        this(new RedisMetricsStorage(), new Aggregator2(), new EmailViewer(emailToAddresses));
    }

    public void startDailyReport() {
        Date firstTime = trimTimeFieldsToZeroOfNextDay(new Date());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos =
                        metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                Map<String, RequestStat> stats = aggregator2.aggregate(requestInfos, durationInMillis);
                viewer.output(stats, startTimeInMillis, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }

    // 设置成protected而非private是为了方便写单元测试
    @VisibleForTesting
    Date trimTimeFieldsToZeroOfNextDay(Date date) {
        Calendar calendar = Calendar.getInstance(); // 这里可以获取当前时间
        calendar.setTime(date); // 重新设置时间
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }



}
