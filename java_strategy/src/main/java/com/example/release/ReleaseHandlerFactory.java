package com.example.release;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cp
 * @date: 2023-09-24 23:00
 */
@Component
public class ReleaseHandlerFactory {

    protected static Map<Integer, ReleaseHandler> handlerFactory = new HashMap<>();

    public static ReleaseHandler produce(Integer category) {
        return handlerFactory.get(category);
    }

    public static synchronized void addHandler2Factory(Integer category, ReleaseHandler handler) {

        if (null == handlerFactory) {
            handlerFactory = new HashMap<>();
        }
        handlerFactory.put(category, handler);
    }

}
