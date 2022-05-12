package service;

import common.bo.RequestInfo;

import java.util.List;
import java.util.Map;

public interface IMetricsStorageSrv {

    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);

}
