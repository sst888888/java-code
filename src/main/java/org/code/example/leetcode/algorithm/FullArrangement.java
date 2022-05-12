package org.code.example.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FullArrangement
 * @Description 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * @Author Chaiphat
 * @Date 2020/1/28 18:28
 * @Version 1.0
 **/
public class FullArrangement {

    static List<List<Integer>> Alllist = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums){
        Alllist.clear();
        List<Integer> list = new ArrayList<>();
        int[] visit = new int[nums.length];
        dfs(list, nums, visit);
        return Alllist;
    }

    public void dfs(List<Integer> list, int []nums, int []visit){
        if(list.size() == nums.length){
            Alllist.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i < nums.length; i ++){
            if(visit[i] == 0){
                visit[i] = 1;
                list.add(nums[i]);
                dfs(list, nums,visit);
                visit[i] = 0;
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        FullArrangement fullArrangement = new FullArrangement();
        int[] a = new int[] {1,2,3};
        int[] b = {1,2,3};
        List<List<Integer>> result = fullArrangement.permute(b);
        result.forEach(item-> System.out.println(item.toString()));
    }


}
