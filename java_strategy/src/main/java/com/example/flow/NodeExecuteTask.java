package com.example.flow;

import java.util.concurrent.Callable;


public class NodeExecuteTask implements Callable {

    private FlowNodeInterface flowNodeInterface;
    private FlowEngine.RunData runData;
    private Context context;

    public NodeExecuteTask(FlowNodeInterface flowNodeInterface, FlowEngine.RunData runData, Context context) {
        this.flowNodeInterface = flowNodeInterface;
        this.runData = runData;
        this.context = context;
    }

    public Object execute() {
        Object o = flowNodeInterface.invokeNode(runData, context);
        flowNodeInterface.afterInvoke(runData, context);
        return o;
    }


    @Override
    public Object call() throws Exception {
        return execute();
    }
}
