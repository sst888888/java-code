package service;

import common.bo.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisMetricsStorageSrv
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 16:55
 * @Version 1.0
 **/
public class RedisMetricsStorageSrv implements IMetricsStorageSrv{
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
