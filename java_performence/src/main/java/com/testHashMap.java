package com;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName testHashMap
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/18 16:36
 * @Version 1.0
 **/
@Slf4j
public class testHashMap {

    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>(16);
        log.info("size = {}", map.size());
        map.put("a","18");
        log.info("size = {}", map.size());

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(1);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.removeIf(Predicate.isEqual(1));
        log.info("{}", objects);


        // 会异常
        // 在返回空的List时  不要new 而是返回 Collections.emptyList()
        // 在返回空的Map时  不要new 而是返回 Collections.emptyMap();
        // 创建和回收都有开销
        List<Object> emptyList = Collections.emptyList();
        emptyList.add(1);

    }
}
