package com.example.flow;


import com.example.cancel.CancelOrderService;
import com.example.cancel.CancelOrderServiceStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@Slf4j
@RestController
public class NodeController {

    @RequestMapping(value = "/test/flow", method = RequestMethod.GET)
    public String cancel() throws Exception {
        FlowEngine engine = new FlowEngine();
        FlowNode testFlow = Flow.getTestFlow();
        FlowEngine.RunData runData = new FlowEngine.RunData();
        runData.setParamOne("one");
        runData.setParamTwo("two");
        Context context = new Context();
        engine.execute(testFlow, runData, context);
        // 返回结果值
        Map<String, Object> adaptorMap = context.getAdaptorMap();
        log.info("执行结果：{}" , adaptorMap.get("NodeOne"));
        log.info("执行结果：{}" , adaptorMap.get("NodeTwo"));
        return "ok";
    }

//    public static void main(String[] args) throws Exception {
////        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
////        FlowEngine engine = (FlowEngine)applicationContext.getBean("flowEngine");
//        FlowEngine engine = new FlowEngine();
//        FlowNode testFlow = Flow.getTestFlow();
//        FlowEngine.RunData runData = new FlowEngine.RunData();
//        runData.setParamOne("one");
//        runData.setParamTwo("two");
//        Context context = new Context();
//        engine.execute(testFlow, runData, context);
//        // 返回结果值
//        Map<String, Object> adaptorMap = context.getAdaptorMap();
//        log.info("执行结果：{}" , adaptorMap.get("NodeOne"));
//        log.info("执行结果：{}" , adaptorMap.get("NodeTwo"));
//    }

    public static class Flow {
        private static FlowNode testFlow = new FlowNode();
        static {
            testFlow.add(NodeOne.class, new FlowNode.NodeConf());
            testFlow.add(NodeTwo.class, new FlowNode.NodeConf());
            testFlow.add("three", NodeOne.class, new FlowNode.NodeConf());
            testFlow.add("three", NodeTwo.class, new FlowNode.NodeConf());
        }
        public static FlowNode getTestFlow(){
            return testFlow;
        }
    }
}
