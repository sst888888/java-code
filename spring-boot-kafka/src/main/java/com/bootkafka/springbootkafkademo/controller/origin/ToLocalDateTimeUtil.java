package com.bootkafka.springbootkafkademo.controller.origin;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ToLocalDateTimeUtil {

    public static LocalDateTime parse(String str) {
        if (null == str || str == "") {
            return null;
        }

        int length = str.length();
//        log.info("str = {}, length = {}", str, length);
        if (length == 26 ) {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")); // 26位
        }

        if (length == 19 ) {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 19位
        }

        String s = str + "000";
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")); // 26位
    }
}
