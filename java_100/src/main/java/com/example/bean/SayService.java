package com.example.bean;

import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public abstract class SayService {
    // 模拟SayService是有状态
    List<String> data = new ArrayList<>();

//    @VisibleForTesting
    public void say() {
        data.add(IntStream.rangeClosed(1, 1000000)
                .mapToObj(__ -> "a")
                .collect(Collectors.joining("")) + UUID.randomUUID().toString());
        log.info("I'm {} size:{}", this, data.size());
    }

    public static void main(String[] args) {
        List<Object> list = Collections.emptyList();
        for (Object o : list) {
            log.info("---{}---" , o);
        }

        String s = IntStream.rangeClosed(1, 20).mapToObj(__ -> "a").collect(Collectors.joining("")) + UUID.randomUUID().toString();
        log.info("s is {}" , s);
    }
}
