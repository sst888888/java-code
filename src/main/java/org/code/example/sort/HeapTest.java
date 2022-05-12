package org.code.example.sort;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName HeapTest
 * @Description 静态数据查找 K 大的数据
 * @Author Chaiphat
 * @Date 2020/3/11 11:50
 * @Version 1.0
 **/
public class HeapTest {

    public static void main(String[] args) {
        Integer[] target = { 2, 3, 5, 8, 11, 22, 97, 98, 33, 44, 55, 66, 77, 88, 99, 100, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        PriorityQueue<Integer> queue = new PriorityQueue<>(5);
        queue.addAll(Arrays.asList(1,2,3,4,5));
        for(Integer num : target){
            Integer top = queue.peek();
            if(num > top){
                queue.poll();
                queue.add(num);
            }
        }
        queue.forEach(i-> System.out.println(i));
    }
}
