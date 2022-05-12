package org.code.example.sort;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @ClassName MajorityElement
 * @Description 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * @Author Chaiphat
 * @Date 2020/3/12 11:04
 * @Version 1.0
 **/
public class MajorityElement {

    /**
     * @note 求众数
     * @apiNote  leetcode上说明,一定是具有众数存在的，则可以计算出一个初始值 count,
     * 从数组的下标1开始，如果与下一个数组下标的数字不相等，则count-- 当count 为0 就切换为下一个比较的数字
     */

    private static int majorityElement(int[] nums){
        int count = 1;
        int maj = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(maj == nums[i]){
                continue;
            }else {
                count--;
                if(count == 0){
                    maj = nums[i+1];
                }
            }
        }
        return maj;
    }

    /**
     * 用哈希计数
     * @param nums
     * @return
     */
    private static int majorityElement1(int[] nums){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(map.get(num) == null){
                map.put(num,1);
            }else {
                map.put(num,map.get(num)+1);
            }
        }

        int target = nums.length/2;
        for(Integer key : map.keySet()){
            if(map.get(key) > target){
                return key;
            }
        }
        return -1;
    }

    /**
     * Boyer-Moore 投票算法
     * 出现次数大于n/2的元素，出现次数和剩余的其他元素的出现次数相互抵消，最后还多一个。
     * 这个题目保证了一定有众数。
     * @param nums
     * @return
     */
    private static int majorityElement2(int[] nums){
        int num = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != num){
                if(count == 0){
                    num = nums[i];
                    count = 1;
                }else {
                    count--;
                }
            }else {
                count++;
            }
        }
        return num;
    }

    /**
     * 分治法：找到左右两边两个子数组的众数a和b。如果a=b，则众数=a；如果a！=b，谁的个数多，谁是众数。
     * 有个问题是：子数组中不一定有众数。如果a的个数和b的个数一样多，怎么办？
     * @param nums
     * @return
      */
    private static int majorityElement3(int[] nums){
        return findMajorityElement(nums,0,nums.length - 1);
    }

    private static int countInRange(int[] nums, int low, int high, int val){
        int count = 0;
        for(int i = low; i <= high; i++){
            if(nums[i] == val){
                count++;
            }
        }
        return count;
    }

    private static int findMajorityElement(int[] nums, int low, int high){
        if(low == high){
            return nums[low];
        }
        int middle = (low+high)/2;
        int a = findMajorityElement(nums,low,middle);
        int b = findMajorityElement(nums,middle+1,high);
        if(a == b){
            return a;
        }

        int countA = countInRange(nums, low, middle, a);
        int countB = countInRange(nums, middle+1, high, b);

        return countA > countB ? a : b;
    }

    /**
     * 排序之后，众数一定位于 n/2 的位置
     * @param nums
     * @return
     */
    private static int majorityElement4(int[] nums){
        Arrays.sort(nums);
        return nums[nums.length/2];
    }



    public static void main(String[] args){
        int[] nums = new int[]{3,2,3};
        int element = majorityElement4(nums);
        System.out.println(element);

        // new MajorityElement().majorityElement(new int[]{3,2,3});
    }

}
