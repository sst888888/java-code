package org.code.example.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PerfectSquare
 * @Description 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, …）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 输入: n = 12
 * 输出: 3
 * 解释：12 = 4 + 4 + 4
 * @Author Chaiphat
 * @Date 2020/2/1 16:49
 * @Version 1.0
 **/
public class PerfectSquare {


    public boolean isPowerOfFour(int num){
        Map<String, String> vars = Collections.singletonMap("hotel", "42");
        return Integer.toString(num,4).matches("10*");
    }

    public int numSquares(int n){
        List<Integer> squareList = generateSquareList(n);
        int []dp = new int[n + 1];
        for(int i = 1; i <= n; i++){
            int min = Integer.MAX_VALUE;
            for(int square : squareList){
                if(square > i){
                    break;
                }
                min = Math.min(min, dp[i - square] + 1);
            }
            dp[i] = min;
        }
        for(int k = 1; k< dp.length; k++){
            System.out.print(dp[k]);
        }
        return dp[n];
    }

    private List<Integer> generateSquareList(int n){
        List<Integer> squareList = new ArrayList<>();
        int diff = 3;
        int square = 1;
        while(square <= n){
            squareList.add(square);
            square += diff;
            diff += 2;
        }
        System.out.println(squareList.toString());
        return squareList;
    }

    public static void main(String[] args) {
        PerfectSquare perfectSquare = new PerfectSquare();
        int i = perfectSquare.numSquares(12);
        System.out.println(i);
        System.out.println(perfectSquare.isPowerOfFour(166));
    }

}
