import common.bo.RequestInfo;
import common.com.MetricsCollector;
import common.factory.ReporterFactory;
import common.reporter.ConsoleReporter;
import common.storage.MetricsStorage;
import common.storage.MockRedisMetricsStorage;
import common.util.Aggregator2;
import common.viewer.ConsoleViewer;
import org.junit.Test;

/**
 * @ClassName DemoV2
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 21:33
 * @Version 1.0
 **/
public class DemoV2 {
    public static void main(String[] args) {
        MetricsStorage storage = new MockRedisMetricsStorage();
        ConsoleReporter consoleReporter = ReporterFactory.createConsoleReporter(storage);
        consoleReporter.startRepeatedReport(2, 2);

//    EmailReporter emailReporter = new EmailReporter(storage);
//    emailReporter.addToAddress("wangzheng@xzg.com");
//    emailReporter.startDailyReport();

        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123.0, 10234L));
        collector.recordRequest(new RequestInfo("register", 223.0, 11234L));
        collector.recordRequest(new RequestInfo("register", 323.0, 12334L));
        collector.recordRequest(new RequestInfo("login", 23.0, 12434L));
        collector.recordRequest(new RequestInfo("login", 1223.0, 14234L));

//    try {
//      Thread.sleep(1000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    }

    @Test
    public void testConsoleReporter() {
        MetricsStorage storage = new MockRedisMetricsStorage();
        Aggregator2 aggregator = new Aggregator2();

        // 定时触发统计并将结果显示到终端
        ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, new ConsoleViewer());
        consoleReporter.startRepeatedReport(2, 2);

        // 收集接口访问数据
        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123.0, 10234L));
        collector.recordRequest(new RequestInfo("register", 223.0, 11234L));
        collector.recordRequest(new RequestInfo("register", 323.0, 12334L));
        collector.recordRequest(new RequestInfo("login", 23.0, 12434L));
        collector.recordRequest(new RequestInfo("login", 1223.0, 14234L));

        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }

    @Test
    public void testEmailReporter() {
        MetricsStorage storage = new MockRedisMetricsStorage();
        Aggregator2 aggregator = new Aggregator2();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, new ConsoleViewer());
        consoleReporter.startRepeatedReport(2, 2);
        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123.0, 10234L));
        collector.recordRequest(new RequestInfo("register", 223.0, 11234L));
        collector.recordRequest(new RequestInfo("register", 323.0, 12334L));
        collector.recordRequest(new RequestInfo("login", 23.0, 12434L));
        collector.recordRequest(new RequestInfo("login", 1223.0, 14234L));

    }


}
