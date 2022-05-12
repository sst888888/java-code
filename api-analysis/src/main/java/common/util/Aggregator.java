package common.util;

import common.bo.RequestInfo;
import common.bo.RequestStat;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * @ClassName Aggregator
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 15:36
 * @Version 1.0
 **/
public class Aggregator {

    public static RequestStat aggregate(List<RequestInfo> requestInfos, long durationInMillis){

        if(CollectionUtils.isEmpty(requestInfos)){
            return null;
        }

        double maxRespTime = Double.MIN_VALUE;
        double minRespTime = Double.MAX_VALUE;
        double avgRespTime = -1;
        double p999RespTime = -1;
        double p99RespTime = -1;
        double sumRespTime = 0;

        int count = 0;
        for (RequestInfo requestInfo : requestInfos) {
            ++count;
            double responseTime = requestInfo.getResponseTime();
            if(maxRespTime < responseTime){
                maxRespTime = responseTime;
            }

            if(minRespTime > responseTime){
                minRespTime = responseTime;
            }

            sumRespTime += responseTime;
        }

        long tps = count / durationInMillis * 1000;

        requestInfos.sort((o1,o2)->{
            double diff = o1.getResponseTime() - o2.getResponseTime();
            return Double.compare(diff,0.0);
        });
        requestInfos.sort(Comparator.comparingDouble(RequestInfo::getResponseTime));

        int idx999 = (int) (count * 0.999);
        int idx99 = (int) (count * 0.99);
        if (count != 0) {
            p999RespTime = requestInfos.get(idx999).getResponseTime();
            p99RespTime = requestInfos.get(idx99).getResponseTime();
            avgRespTime = sumRespTime / count;
        }
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(maxRespTime);
        requestStat.setMinResponseTime(minRespTime);
        requestStat.setAvgResponseTime(avgRespTime);
        requestStat.setP999ResponseTime(p999RespTime);
        requestStat.setP99ResponseTime(p99RespTime);
        requestStat.setCount(count);
        requestStat.setTps(tps);
        return requestStat;
    }

}
