package com.example.flow;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 上下文 调用下游服务的返回结果
 */
public class Context {

    /**
     * 结果缓存
     */
    private Map<String, Object> resultMap = Maps.newHashMap();

    public Map<String, Object> getAdaptorMap() {
        return resultMap;
    }

    public void setAdaptorMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

}
