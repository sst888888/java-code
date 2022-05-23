package com;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @ClassName test
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/17 21:45
 * @Version 1.0
 **/
@Slf4j
public class testStreamParallel {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }
//        Collections.sort(integerList,Collections.reverseOrder());
        integerList.forEach(integer -> log.info("---{}", integer));

        log.info("-------------ArrayList--------------------");
        // 多线程并发问题 因为ArrayList是线程不安全的
        List<Integer> parallelList = new ArrayList<>();
        integerList.stream().parallel().filter(i -> i % 2 == 1).forEach(parallelList::add);
        parallelList.forEach(integer -> log.info("---{}", integer));


        log.info("-------------copyOnWriteArrayList--------------------");
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        integerList.stream().parallel().filter(i -> i % 2 == 1).forEach(copyOnWriteArrayList::add);
        copyOnWriteArrayList.forEach(integer -> log.info("---{}", integer));


        log.info("-------------vector--------------------");
        Vector<Integer> vector = new Vector<>();
        integerList.stream().parallel().filter(i -> i % 2 == 1).forEach(vector::add);
        Collections.sort(vector);
        vector.forEach(integer -> log.info("---{}", integer));

        Collections.sort(vector,Collections.reverseOrder());
        vector.forEach(integer -> log.info("---{}", integer));

        log.info("-------------collect--------------------");
        List<Integer> collect = integerList.stream().parallel().filter(i -> i % 2 == 1).collect(Collectors.toList());
        collect.forEach(integer -> log.info("---{}", integer));

    }
}
