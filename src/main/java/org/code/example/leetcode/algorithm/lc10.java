package org.code.example.leetcode.algorithm;

/**
 * @ClassName lc10
 * @Description
 * @Author Chaiphat
 * @Date 2020/2/8 19:55
 * @Version 1.0
 **/
public class lc10 {

    public static void main(String[] args) {
        System.out.println(isMatch("aaa","ab*a*c*a"));
    }


    public static boolean isMatch(String s,String p){
        boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i < p.length()+1; i++){
            if(p.charAt(i-1)=='*'){
                if(i > 1 && dp[0][i-2]){
                    dp[0][i] = true;
                }
            }
        }

        for(int i = 1; i < s.length()+1; i++){
            for(int j = 1; j < p.length()+1; j++){
                if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                if(p.charAt(j-1)=='*'){
                    if(s.charAt(i-1)!=p.charAt(j-2) && p.charAt(j-2)!='.'){
                        dp[i][j] = dp[i][j-2];
                    }
                    else {
                        dp[i][j] = (dp[i-1][j] || dp[i-1][j-1] || dp[i][j-2]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
