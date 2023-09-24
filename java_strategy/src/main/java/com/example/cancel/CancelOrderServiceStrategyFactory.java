package com.example.cancel;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: cp
 * @date: 2023-09-24 22:42
 */
@Component
public class CancelOrderServiceStrategyFactory {
    private static ConcurrentHashMap<Integer, CancelOrderService> map = new ConcurrentHashMap<>();

    private CancelOrderServiceStrategyFactory() {

    }

    public static CancelOrderService getByCategory(Integer category) {
        return map.get(category);
    }

    public static synchronized void register(Integer category, CancelOrderService cancelOrderService) {
        Assert.notNull(category, "category can't be null");
        if (null == map) {
            map = new ConcurrentHashMap<>();
        }
        map.put(category, cancelOrderService);
    }

}
