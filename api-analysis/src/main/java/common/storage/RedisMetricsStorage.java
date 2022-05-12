package common.storage;

import common.bo.RequestInfo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisMetricsStorage
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 21:15
 * @Version 1.0
 **/
public class RedisMetricsStorage implements MetricsStorage {
    //...省略属性和构造函数等...
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        //...
        System.out.println("RedisMetricsStorage saveRequestInfo");
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimestamp, long endTimestamp) {
        //...
        System.out.println("getRequestInfos");
        return Collections.emptyList();
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimestamp, long endTimestamp) {
        //...
        System.out.println("getRequestInfos");
        return null;
    }
}
