package common.viewer;

import com.google.gson.Gson;
import common.bo.RequestStat;

import java.util.Map;

/**
 * @ClassName ConsoleViewer
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 20:26
 * @Version 1.0
 **/
public class ConsoleViewer implements StatViewer {
    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("ConsoleViewer ... ");
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        Gson gson = new Gson();
        System.out.println("requestStats == " + gson.toJson(requestStats));
    }
}
