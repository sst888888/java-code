package common.factory;

import common.reporter.ConsoleReporter;
import common.storage.MetricsStorage;
import common.util.Aggregator2;
import common.viewer.ConsoleViewer;

/**
 * @ClassName ReporterFactory
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 21:35
 * @Version 1.0
 **/
public class ReporterFactory {

    public static ConsoleReporter createConsoleReporter(MetricsStorage storage) {
        Aggregator2 aggregator = new Aggregator2();
        return new ConsoleReporter(storage, aggregator, new ConsoleViewer());
    }


}
