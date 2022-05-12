package org.code.example.leetcode.algorithm;

/**
 * @ClassName SolutionMaxSubArray
 * @Description 求最大子序和
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * @Author Chaiphat
 * @Date 2020/1/31 20:26
 * @Version 1.0
 **/
public class SolutionMaxSubArray {

    public int maxSubArray(int[] nums){
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i ++){
            dp[i] = Math.max(dp[i - 1] + nums[i],nums[i]);
        }
        int max = nums[0];
        for(int i = 0; i < dp.length; i ++){
            System.out.println("i=" + i + "," + "dp[i]=" + dp[i]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        SolutionMaxSubArray entity = new SolutionMaxSubArray();
        int []arr = {-2,1,-3,4,-1,2,1,-5,4};
        int max = entity.maxSubArray(arr);
        System.out.println(max);
    }

}
