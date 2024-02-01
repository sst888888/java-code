package com.example.cmd;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 定义抽象校验方法
 */
@Component
public abstract class ValidatePlugin implements Callable {

    public abstract void validate();

    protected static List<ValidatePlugin> validatePlugins = Lists.newArrayList();


    public static List<ValidatePlugin> getAllList() {
        return validatePlugins;
    }
}
