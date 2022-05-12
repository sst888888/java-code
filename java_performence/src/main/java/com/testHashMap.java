package com;

import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.function.Predicate;

/**
 * @ClassName testHashMap
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/18 16:36
 * @Version 1.0
 **/
public class testHashMap {

    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>(16);
        System.out.println(map.size());
        map.put("a","18");
        System.out.println(map.size());

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(1);

        // 会异常
        // 在返回空的List时  不要new 而是返回 Collections.emptyList()
        // 在返回空的Map时  不要new 而是返回 Collections.emptyMap();
        // 创建和回收都有开销
        List<Object> emptyList = Collections.emptyList();
        emptyList.add(1);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.removeIf(Predicate.isEqual(1));
        System.out.println(objects);

    }
}
