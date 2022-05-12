package com;

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
public class testStreamParallel {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }
//        Collections.sort(integerList,Collections.reverseOrder());
        integerList.forEach(integer -> System.out.println(integer));

        System.out.println("-------------ArrayList--------------------");
        // 多线程并发问题 因为ArrayList是线程不安全的
        List<Integer> parallelList = new ArrayList<>();
        integerList.stream().parallel().filter(i -> i % 2 == 1).forEach(i -> parallelList.add(i));
        parallelList.forEach(integer -> System.out.println(integer));


        System.out.println("-------------copyOnWriteArrayList--------------------");
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        integerList.stream().parallel().filter(i -> i % 2 == 1).forEach(i -> copyOnWriteArrayList.add(i));
        copyOnWriteArrayList.forEach(integer -> System.out.println(integer));


        System.out.println("-------------vector--------------------");
        Vector<Integer> vector = new Vector<>();
        integerList.stream().parallel().filter(i -> i % 2 == 1).forEach(i -> vector.add(i));
        Collections.sort(vector);
        vector.forEach(integer -> System.out.println(integer));

        Collections.sort(vector,Collections.reverseOrder());
        vector.forEach(integer -> System.out.println(integer));

        System.out.println("-------------collect--------------------");
        List<Integer> collect = integerList.stream().parallel().filter(i -> i % 2 == 1).collect(Collectors.toList());
        collect.forEach(integer -> System.out.println(integer));

    }
}
