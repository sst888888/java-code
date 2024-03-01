package com.bootkafka.springbootkafkademo.controller.origin;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentHashMap;


@Component
public class ExeFactory {

    private static ConcurrentHashMap<String, ExeService> map = new ConcurrentHashMap<>();

    private ExeFactory() {

    }

    public static ExeService getByFileName(String fileName) {
        return map.get(fileName);
    }

    public static synchronized void register(String fileName, ExeService exeService) {
        Assert.notNull(fileName, "fileName can't be null");
        if (null == map) {
            map = new ConcurrentHashMap<>();
        }
        map.put(fileName, exeService);
    }

}
