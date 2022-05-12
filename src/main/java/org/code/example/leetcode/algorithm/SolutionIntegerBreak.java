package org.code.example.leetcode.algorithm;

/**
 * @ClassName SolutionIntegerBreak
 * @Description 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 例如，给定 n = 2，返回1（2 = 1 + 1）；给定 n = 10，返回36（10 = 3 + 3 + 4）。
 * 注意：你可以假设 n 不小于2且不大于58
 * @Author Chaiphat
 * @Date 2020/1/31 20:47
 * @Version 1.0
 **/
public class SolutionIntegerBreak {

    public int integerBreak(int n){
        int []dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i-1; j++){
                dp[i] = Math.max(dp[i],Math.max(j*dp[i-j],j*(i-j)));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {

        SolutionIntegerBreak entity = new SolutionIntegerBreak();
        System.out.println(entity.integerBreak(10));
    }

}
