package com.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @ClassName PropertiesUtil
 * @Description
 * @Author Chaiphat
 * @Date 2020/10/18 15:56
 * @Version 1.0
 **/
@Slf4j
public class PropertiesUtil {

 private static Properties props;

 static {
     String fileName = "mmall.properties";
     props = new Properties();
     try {
         props.load(new InputStreamReader(Properties.class.getResourceAsStream(fileName)));
     } catch (IOException e) {
         log.error("配置文件读取异常", e);
     }
 }

 public static String getProperty(String key) {
     String value = props.getProperty(key.trim());
     if(StringUtils.isEmpty(value)) {
         return null;
     }
     return value.trim();
 }

    public static String getProperty(String key, String defaultValue ) {
        String value = props.getProperty(key.trim());
        if(StringUtils.isEmpty(value)) {
            value = defaultValue;
        }
        return value.trim();
    }


    public static void main(String[] args) {
        String property = PropertiesUtil.getProperty("redis.max.total", "20");
        System.out.println(property);
    }




}
