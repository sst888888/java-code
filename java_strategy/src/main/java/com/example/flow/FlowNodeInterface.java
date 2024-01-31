package com.example.flow;

/**
 * @author: cp
 * @date: 2024-01-30 22:05
 */
public interface FlowNodeInterface <T>{

    /**
     * node的执行方法
     */
    T invokeNode(FlowEngine.RunData nodeData, Context context);

    /**
     * node执行完后执行方法
     */
    void afterInvoke(FlowEngine.RunData nodeData, Context context);

    /**
     * 从context中获取此node结果的key
     */
    String resultKey();

}
