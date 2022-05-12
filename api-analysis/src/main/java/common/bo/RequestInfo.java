package common.bo;

/**
 * @ClassName RequestInfo
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 15:37
 * @Version 1.0
 **/
public class RequestInfo {

    private String apiName;
    private double responseTime;
    private Long timestamp;

    public RequestInfo(String apiName, double responseTime, Long timestamp) {
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.timestamp = timestamp;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
