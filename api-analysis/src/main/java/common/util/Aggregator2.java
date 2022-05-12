package common.util;

import common.bo.RequestInfo;
import common.bo.RequestStat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Aggregator2
 * @Description 重构后的版本 V2.0
 * @Author Chaiphat
 * @Date 2020/6/27 20:20
 * @Version 1.0
 **/
public class Aggregator2 {

    public Map<String, RequestStat> aggregate(
            Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            requestStats.put(apiName, requestStat);
        }
        return requestStats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis/1000));
        return requestStat;
    }

    // 以下的函数的代码实现均省略...
    private double max(List<Double> dataset) {
        return Double.valueOf(0);
    }
    private double min(List<Double> dataset) {return Double.valueOf(0);}
    private double avg(List<Double> dataset) {return Double.valueOf(0);}
    private double tps(int count, double duration) {return Double.valueOf(0);}
    private double percentile999(List<Double> dataset) {return Double.valueOf(0);}
    private double percentile99(List<Double> dataset) {return Double.valueOf(0);}
    private double percentile(List<Double> dataset, double ratio) {return Double.valueOf(0);}


}
