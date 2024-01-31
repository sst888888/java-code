package com.example.flow;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class FlowEngine {

    public static ThreadFactory getNewThreadFactory(String threadName) {
        return r -> new Thread(r, StringUtils.join(threadName, "_pool_", r.hashCode()));
    }
    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10,
            60L, TimeUnit.MINUTES, new SynchronousQueue<>(), getNewThreadFactory("enjine proccessor"),
           new ThreadPoolExecutor.AbortPolicy(){
                public void rejectedException() {

                }
           }
    );

    private Map<String, List<String>> groupByGroupName(FlowNode flowNode) {
        Map<String, List<String>> nodeGroup = Maps.newLinkedHashMap();
        for (String nodeKey : flowNode.getNodeList()) {
            String groupName = getGroupName(nodeKey);
            String nodeName = getNodeName(nodeKey);
            if (StringUtils.isBlank(groupName)) {
                List<String> nodeNameList = Lists.newArrayList();
                nodeNameList.add(nodeName);
                nodeGroup.put(nodeName, nodeNameList);
            } else {
                List<String> nodeNameList = nodeGroup.get(groupName);
                if (nodeNameList == null) {
                    nodeNameList = Lists.newArrayList();
                }
                nodeNameList.add(nodeName);
                nodeGroup.put(groupName, nodeNameList);
            }
        }

        return nodeGroup;
    }

    private String getGroupName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[0] : null;
    }

    private String getNodeName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[1] : null;
    }

    /**
     * 引擎执行入口
     */
    public void execute(FlowNode flowNode, RunData runData, Context context) throws Exception {
        Map<String, List<String>> nodeGroup = groupByGroupName(flowNode);
        Map<String, FlowNode.NodeConf> nodeMap = flowNode.getNodeMap();
        for (String groupName : nodeGroup.keySet()) {
            boolean needThrowExp = false;
            List<String> nodeNameList = nodeGroup.get(groupName);
            // 只有一个node节点 串行执行
            if (nodeNameList.size() == 1) {
                String nodeName = nodeNameList.get(0);
                FlowNodeInterface detailNode = (FlowNodeInterface)BeanService.getSingleBeanByType(Class.forName(nodeName));
                NodeExecuteTask nodeExecuteTask = new NodeExecuteTask(detailNode, runData, context);
                try {
                    Object result = nodeExecuteTask.execute();
                    context.getAdaptorMap().put(detailNode.resultKey(), result);
                } catch (Exception e) {
                    needThrowExp = true;
                }
            } else {
                // 多个Node节点 并行执行
                List<Future> resultList = new ArrayList<>();
                List<String> executeNodeNameList = new ArrayList<>();
                List<NodeExecuteTask> executeNodeList = new ArrayList<>();
                for (String nodeName : nodeNameList) {
                    FlowNodeInterface detailNode = (FlowNodeInterface)BeanService.getSingleBeanByType(Class.forName(nodeName));
                    NodeExecuteTask nodeExecuteTask = new NodeExecuteTask(detailNode, runData, context);
                    executeNodeList.add(nodeExecuteTask);
                    executeNodeNameList.add(nodeName);
                    resultList.add(threadPool.submit(nodeExecuteTask));
                }

                for (int i = 0; i < resultList.size(); i++) {
                    String nodeName = executeNodeNameList.get(i);
                    String nodeKey = groupName + "_" + nodeName;
                    FlowNodeInterface detailNode = (FlowNodeInterface)BeanService.getSingleBeanByType(Class.forName(nodeName));
                    FlowNode.NodeConf nodeConf = nodeMap.get(nodeKey);
                    int timeOut = nodeConf.getTimeOut();
                    Future future = resultList.get(i);
                    Object o = future.get(timeOut, TimeUnit.MICROSECONDS);
                    context.getAdaptorMap().put(detailNode.resultKey(), o);
                }
            }

            if (needThrowExp) {
                throw new RuntimeException();
            }
        }
    }

    /**
     * 流程中的参数
     */
    @Data
    public static class RunData{
        private String paramOne;
        private String paramTwo;
    }



}
