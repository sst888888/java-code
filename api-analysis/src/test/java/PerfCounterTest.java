import common.bo.RequestInfo;
import common.com.MetricsCollector;
import common.reporter.ConsoleReporter;
import common.reporter.EmailReporter;
import common.storage.MetricsStorage;
import common.storage.MockRedisMetricsStorage;
import common.storage.RedisMetricsStorage;
import common.util.Aggregator2;
import common.viewer.ConsoleViewer;
import common.viewer.EmailViewer;
import org.junit.Test;

/**
 * @ClassName PerfCounterTest
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 21:09
 * @Version 1.0
 **/
public class PerfCounterTest {

    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        Aggregator2 aggregator = new Aggregator2();

        // 定时触发统计并将结果显示到终端
        ConsoleViewer consoleViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, consoleViewer);
        consoleReporter.startRepeatedReport(60, 60);

        // 定时触发统计并将结果输出到邮件
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("wangzheng@xzg.com");
        EmailReporter emailReporter = new EmailReporter(storage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        // 收集接口访问数据
        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123.00, 10234L));
        collector.recordRequest(new RequestInfo("register", 223.00, 11234L));
        collector.recordRequest(new RequestInfo("register", 323.00, 12334L));
        collector.recordRequest(new RequestInfo("login", 23.00, 12434L));
        collector.recordRequest(new RequestInfo("login", 1223.00, 14234L));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMain(){
        ConsoleReporter consoleReporter = new ConsoleReporter();
        consoleReporter.startRepeatedReport(2, 2);

        MetricsCollector collector = new MetricsCollector(new MockRedisMetricsStorage());
        collector.recordRequest(new RequestInfo("register", 123.00, 10234L));
        collector.recordRequest(new RequestInfo("register", 223.00, 11234L));
        collector.recordRequest(new RequestInfo("register", 323.00, 12334L));
        collector.recordRequest(new RequestInfo("login", 23.00, 12434L));
        collector.recordRequest(new RequestInfo("login", 1223.00, 14234L));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
