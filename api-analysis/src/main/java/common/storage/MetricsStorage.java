package common.storage;

import common.bo.RequestInfo;

import java.util.List;
import java.util.Map;

public interface MetricsStorage {

    void saveRequestInfo(RequestInfo requestInfo);
    List getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);
    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
