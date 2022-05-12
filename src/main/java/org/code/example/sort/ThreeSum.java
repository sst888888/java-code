package org.code.example.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ThreeSum
 * @Description 一个数组中 找出3数之和为0的元组
 * @Author Chaiphat
 * @Date 2020/3/11 21:47
 * @Version 1.0
 **/
public class ThreeSum {

    /**
     * 暴力
     * @param nums
     * @return
     */
    private static List<List<Integer>> threeSum(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n;i++){
            if(i> 0 && nums[i] == nums[i-1]){
                continue;
            }

            for(int j = i + 1; j < n;j++){
                if(j > i + 1 && nums[j] == nums[j-1]){
                    continue;
                }
                for(int k = j + 1; k < n; k++){
                    if(k > j + 1 && nums[k] == nums[k-1]){
                        continue;
                    }
                    if(nums[i] + nums[j] + nums[k] == 0){
                        result.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    }
                }
            }
        }
        return result;
    }


    /**
     * 思路：先排序，然后有2个指针。要计算的第一个数一定是负数，所以只要后2个数的和等于0-第一个数 即可。
     * @param nums
     * @return
     */
    private static List<List<Integer>> threeSum1(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        // 进行排序
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                return result;
            }

            // 之前的相等，选择跳过
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            int header = i + 1;
            int end = nums.length - 1;
            int val = 0 - nums[i];
            while(header < end){
                if(nums[header] + nums[end] == val){
                    List<Integer> list = Arrays.asList(nums[i],nums[header],nums[end]);
                    result.add(list);
                    while(header < end && nums[end] == nums[end-1]){
                        end--;
                    }
                    while(header < end && nums[header] == nums[header+1]){
                        header++;
                    }
                    end--;
                    header++;
                }else if (nums[header] + nums[end] > val){
                    end--;
                }else {
                    header++;
                }
            }
        }
        return result;
    }

    private void twoSum(int[] nums, int low, int high, int target, int num, List<List<Integer>> solution) {
        int i = low;
        int j = high;
        int sum;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                solution.add(Arrays.asList(num, nums[i], nums[j]));
                int x = nums[i];
                while (++i < j && nums[i] == x){}
                int y = nums[j];
                while (i < --j && y == nums[j]){}
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
    }

    /**
     * 在使用2个指针的同时，加上一些逻辑判断，减少计算量
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null){
            return result;
        }

        int len = nums.length;
        if(len < 3){
            return result;
        }

        Arrays.sort(nums);

        int target = 0;
        int k = 3;
        int maxValue = nums[len - 1];
        if(k*nums[0] > target || k*nums[len-1] < target){
            return result;
        }

        int num;
        for(int i = 0; i < len; i++){
            num = nums[i];
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            if((num + 2*maxValue) < target){
                continue;
            }
            if(3*num > target){
                continue;
            }
            twoSum(nums,i+1,len-1,target-num,num,result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4, -1};
        List<List<Integer>> list = threeSum1(nums);
        System.out.println(list);
    }

}
