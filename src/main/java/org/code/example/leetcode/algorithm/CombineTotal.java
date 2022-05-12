package org.code.example.leetcode.algorithm;

import java.util.*;

/**
 * @ClassName CombineTotal
 * @Description 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *              candidates 中的数字可以无限制重复被选取。
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * @Author Chaiphat
 * @Date 2020/1/28 20:50
 * @Version 1.0
 **/
public class CombineTotal {

    static List<List<Integer>> Alllist = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target){
        Alllist.clear();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(list, candidates, 0, target, 0);
        return Alllist;
    }

    public void dfs(List<Integer> list, int[] candidates, int sum, int target, int cur){
        if(sum == target){
            Alllist.add(new ArrayList<>(list));
            return;
        }

        // 由于组合中没有重复数字，并且每个数可以出现多次，所以我们依然可以按顺序进行组合，每次i取自己或比自己更大的数组下标即可。
        for(int i = cur; i < candidates.length; i ++){
            if(sum + candidates[i] <= target){
                list.add(candidates[i]);
                dfs(list, candidates, sum + candidates[i], target, i);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombineTotal combineTotal = new CombineTotal();
        int[] arr = {2,3,5};
        List<Long> list = new ArrayList<>();
        list.add(12L);
        list.add(2L);
        list.add(3L);
        Collections.sort(list);
        System.out.println(list.toString());
        List<List<Integer>> result = combineTotal.combinationSum(arr, 8);
        result.forEach(item-> System.out.println(item.toString()));
    }








}
