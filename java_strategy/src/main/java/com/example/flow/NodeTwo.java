package com.example.flow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NodeTwo implements FlowNodeInterface{
    @Override
    public Object invokeNode(FlowEngine.RunData nodeData, Context context) {
        log.info("执行方法：{}", nodeData.getParamTwo());
        return nodeData.getParamTwo();
    }

    @Override
    public void afterInvoke(FlowEngine.RunData nodeData, Context context) {
        // TODO document why this method is empty
    }

    @Override
    public String resultKey() {
        return "NodeTwo";
    }
}
