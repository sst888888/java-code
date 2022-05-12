package common.storage;

import common.bo.RequestInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MockRedisMetricsStorage
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 21:34
 * @Version 1.0
 **/
public class MockRedisMetricsStorage implements MetricsStorage {

    Map<String, List<RequestInfo>> map = new HashMap<>();

    //...省略属性和构造函数等...
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

//        if (map.containsKey(requestInfo.getApiName())) {
//            map.get(requestInfo.getApiName()).add(requestInfo);
//        } else {
//            map.put(requestInfo.getApiName(), new ArrayList<>());
//            map.get(requestInfo.getApiName()).add(requestInfo);
//        }
        map.putIfAbsent(requestInfo.getApiName(), new ArrayList<>());
        map.get(requestInfo.getApiName()).add(requestInfo);
        System.out.println("MockRedisMetricsStorage saveRequestInfo");
//        map.forEach((key,value)->{
//            System.out.println("key = " + key);
//            System.out.println("value = " + value);
//        });
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimestamp, long endTimestamp) {
        //...
        System.out.println("MockRedisMetricsStorage getRequestInfos");
        return map.get(apiName);
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimestamp, long endTimestamp) {
        //...
        System.out.println("first ---> MockRedisMetricsStorage getRequestInfos");
        return map;
    }
}
