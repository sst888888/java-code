package org.code.example.leetcode.algorithm;

/**
 * @ClassName MaxConsecutiveOnes
 * @Description 找出数组中最长的连续1
 * @Author Chaiphat
 * @Date 2020/1/25 20:31
 * @Version 1.0
 **/
public class MaxConsecutiveOnes {

    /**
     * 待修改 TODO
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums){
        int max = 0,cur = 0;
        for (int x:nums){
            cur = x == 0 ? 0 : cur + 1;
            max = Math.max(max,cur);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {0,1,1,1,0,2,3,4,1};
        MaxConsecutiveOnes maxConsecutiveOnes = new MaxConsecutiveOnes();
        int result = maxConsecutiveOnes.findMaxConsecutiveOnes(a);
        System.out.println(result);
    }
}
