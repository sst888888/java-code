package org.code.example.leetcode.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName lc1
 * @Description
 *  * 1. Two Sum
 *  * 题意：返回数组中和为给定数的下标
 *  * 难度：Easy
 *  * 分类：Array, HashTable
 *  * 算法：题目说明了数组中一定有解，且解唯一，所以用哈希表记录已遍历的元素即可
 * @Author Chaiphat
 * @Date 2020/2/3 21:02
 * @Version 1.0
 **/
public class lc1 {


    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] r = twoSum(nums,target);
        System.out.println(Arrays.toString(r));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length ; i++) {
            if(map.containsKey(nums[i])){
                result[0] = map.get(nums[i]);
                result[1] = i;
                return result;
            }
            map.put(target-nums[i],i);
        }
        return result;
    }

}
