package org.code.example.sort;

/**
 * @ClassName FirstMissingPositive
 * @Description 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * @Author Chaiphat
 * @Date 2020/3/12 12:29
 * @Version 1.0
 **/
public class FirstMissingPositive {
    /**
     * 因为我们要找的是没有出现的最小正整数，所以我们可以忽略 <1的数。
     * 在长度为n的数组中，能够出现的最小正整数是1，最大正整数是n，所以>n的也可以忽略。
     * @param nums
     * @return
     */
    private static int firstMissingPositive(int[] nums){
        boolean findOne = false;
        for(int num : nums){
            if(num == 1){
                findOne = true;
                break;
            }
        }

        if(!findOne){
            return 1;
        }

        /**
         * 小于 1 和大于 n 的置为 1
         */
        for(int i = 0; i < nums.length;i++){
            if(nums[i] < 1){
                nums[i] = 1;
            }else if (nums[i] > nums.length){
                nums[i] = 1;
            }
        }

        for(int i = 0; i < nums.length; i++){
            int num = Math.abs(nums[i]);
            if(nums[num - 1] > 0){
                nums[num - 1] = -nums[num - 1];
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,0};
        int result = firstMissingPositive(nums);
        System.out.println(result);
//
//        int[] nums1 = {3,4,1,-1};
//        int result1 = firstMissingPositive(nums1);
//        System.out.println(result1);

//        int[] nums2 = {7,8,9,11,12};
//        int result2 = firstMissingPositive(nums2);
//        System.out.println(result2);
    }

}
