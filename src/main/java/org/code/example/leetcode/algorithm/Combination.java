package org.code.example.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Combination
 * @Description 给定两个整数 n 和 k，返回 1 … n 中所有可能的 k 个数的组合。
 * 输入： n = 4, k = 2
 * 输出：
 * [1, 2]
 * [1, 3]
 * [1, 4]
 * [2, 3]
 * [2, 4]
 * [3, 4]
 * @Author Chaiphat
 * @Date 2020/1/28 20:10
 * @Version 1.0
 **/
public class Combination {

    static List<List<Integer>> Alllist = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k){
        Alllist.clear();
        List<Integer> list = new ArrayList<>();
        dfs(list, n, k,1);
        return Alllist;
    }

    public void dfs(List<Integer> list, int n, int k, int cur){
        // 长度已经是k 放到结果中
        if(list.size() == k){
            Alllist.add(new ArrayList<>(list));
            return;
        }

        for(int i = cur; i <= n; i ++){
            list.add(i);
            dfs(list, n, k, i + 1);
            list.remove(list.size() - 1);
        }

    }

    public static void main(String[] args) {
        Combination combination = new Combination();
        List<List<Integer>> result = combination.combine(4, 2);
        result.forEach(item -> System.out.println(item.toString()));
    }

}
