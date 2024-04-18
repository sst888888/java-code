package com.example.utils;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 不需要在创建多个Listener
 */
public class ExcelUtil {


    /**
     * 指定阀值
     * @param consumer
     * @param threshold
     * @return
     * @param <T>
     */
    public static <T>AnalysisEventListener<T> getListener(Consumer<List<T>> consumer, int threshold) {
        return new AnalysisEventListener<T>() {
            private final LinkedList<T> linkedList = new LinkedList<T>();

            @Override
            public void invoke(T data, AnalysisContext context) {
                linkedList.add(data);
                if (linkedList.size() == threshold) {
                    consumer.accept(linkedList);
                    linkedList.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                if (!linkedList.isEmpty()) {
                    consumer.accept(linkedList);
                }
            }
        };
    }


    /**
     * 不指定阀值
     * @param consumer
     * @return
     * @param <T>
     */
    public static <T>AnalysisEventListener<T> getListener(Consumer<List<T>> consumer) {
        return getListener(consumer, 10);
    }
}
