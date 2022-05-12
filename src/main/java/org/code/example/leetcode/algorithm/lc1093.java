package org.code.example.leetcode.algorithm;

import java.util.Arrays;

/**
 * @ClassName lc1093
 * @Description 从数组里统计出均值，最大，最小，中位数，众数
 * @Author Chaiphat
 * @Date 2020/2/8 21:22
 * @Version 1.0
 **/
public class lc1093 {

    public static double[] sampleStats(int[] count){
        double[] res = new double[5];
        res[0] = -1;
        res[1] = -1;
        int mode_num = 0;
        int count_num = 0 ;
        for(int i = 0; i < count.length; i++){
            if(res[0]==-1 && count[i]>0){
                res[0] = i;
            }
            if(count[i] > 0 && i > res[1]){
                res[1] = i;
            }
            res[2] += count[i]*i;
            count_num += count[i];
            if(count[i] > mode_num){
                res[4] = i;
                mode_num = count[i];
            }
        }

        // 中位数
        int medium_pos1 = (count_num+1)/2;
        int medium_pos2 = (count_num+2)/2;
        count_num = 0;
        for(int i = 0; i < count.length; i++){
            count_num += count[i];
            if(medium_pos1>0 && medium_pos1<=count_num){
                res[3] += i;
                medium_pos1 = -1;
            }
            if(medium_pos2>0 && medium_pos2<=count_num){
                res[3] += i;
                medium_pos2 = -1;
            }
        }

        res[2] = res[2]/count_num;
        res[3] = res[3]/2;
        return res;
    }

    public static double[] sampleStats2(int[] count) {
        double[] res = new double[5];
        res[0] = -1;
        res[1] = -1;
        int mode_num = 0;
        int count_num = 0;
        for (int i = 0; i < count.length ; i++) {
            if(res[0]==-1 && count[i]>0) res[0] = i;
            if(count[i]>0 && i>res[1]) res[1] = i;
            res[2] += count[i]*i;
            count_num += count[i];
            if(count[i]>mode_num) {
                res[4] = i;
                mode_num = count[i];
            }
        }
        int medium_pos1 = (count_num+1)/2;  //中位数
        int medium_pos2 = (count_num+2)/2;
        count_num = 0;
        for (int i = 0; i < count.length ; i++) {
            count_num += count[i];
            if(medium_pos1>0&&medium_pos1<=count_num) {
                res[3]+= i;
                medium_pos1 = -1;
            }
            if(medium_pos2>0&&medium_pos2<=count_num) {
                res[3]+= i;
                medium_pos2 = -1;
            }
        }
        res[2] = res[2]/count_num;
        res[3] = res[3]/2;
        return res;
    }

    public static void main(String[] args) {
        int a[] = {2,4,7};
        double[] doubles = sampleStats2(a);
        System.out.println(Arrays.toString(doubles));
    }


}
