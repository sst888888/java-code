package com.example.cmd;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ValidateFactory {
    private static ConcurrentHashMap<Integer, ValidatePlugin> map = new ConcurrentHashMap<>();

    private static List<ValidatePlugin> validatePlugins = Lists.newArrayList();

    private ValidateFactory() {

    }

    public static ValidatePlugin getByCategory(Integer category){
        return map.get(category);
    }

    public static synchronized void register(Integer category, ValidatePlugin validatePlugin){
        Assert.notNull(category,"category can't be null");
        if (null == map) {
            map = new ConcurrentHashMap<>();
        }
        map.put(category, validatePlugin);
    }


    public static void add(ValidatePlugin validatePlugin){
       validatePlugins.add(validatePlugin);
    }

    public static List<ValidatePlugin> getAll() {
        return new ArrayList<>(map.values());
    }

    public static List<ValidatePlugin> getAllList() {
        return validatePlugins;
    }
}
