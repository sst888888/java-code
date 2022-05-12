package org.code.example.leetcode.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName lc1094
 * @Description TODO
 * @Author Chaiphat
 * @Date 2020/2/11 21:15
 * @Version 1.0
 **/
public class lc1094 {
    public boolean carPooling(int[][] trips,int capacity){
        Arrays.sort(trips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o1[1] - o2[1];
                }
                else{
                    return o1[2] - o2[2];
                }
            }
        });

        for(int i = 0; i < trips.length; i++){
            int current = trips[i][0];
            for(int j = i - 1; j >= 0;j--){
                if(trips[j][2] > trips[i][1]){
                    current += trips[j][0];
                }
                if(current > capacity){
                    return false;
                }
            }
        }
        return true;
    }



}
