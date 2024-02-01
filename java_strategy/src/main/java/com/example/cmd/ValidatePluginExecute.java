package com.example.cmd;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 抽象规则执行器
 */
@Component
public class ValidatePluginExecute {

    protected List<ValidatePlugin> validatePlugins = new ArrayList<>();
    public static ThreadFactory getNewThreadFactory(String threadName) {
        return r -> new Thread(r, StringUtils.join(threadName, "_pool_", r.hashCode()));
    }
    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10,
            60L, TimeUnit.MINUTES, new SynchronousQueue<>(), getNewThreadFactory("ValidatePluginExecute"),
            new ThreadPoolExecutor.AbortPolicy(){
                public void rejectedException() {

                }
            }
    );

    public void execute() {
//        validatePlugins = ValidateFactory.getAll();
        validatePlugins = ValidateFactory.getAllList();

        List<ValidatePlugin> allList = ValidatePlugin.getAllList();

        if (CollectionUtils.isEmpty(allList)) {
            return;
        }

        for (ValidatePlugin validatePlugin : allList) {
            // 执行校验逻辑
            threadPool.submit(validatePlugin);
        }
    }
}
