package org.code.example.leetcode.algorithm;

/**
 * @ClassName MoveZores
 * @Description Easy
 * @Author Chaiphat
 * @Date 2020/1/25 20:03
 * @Version 1.0
 **/
public class MoveZores {

    /**
     * For example,given nums = [0,1,0,3,12],
     * after call your function, nums should be [1,3,12,0,0]
     * @param nums
     */
    public void moveZeros(int[] nums){
        int index = 0;
        for(int num: nums){
            if(num !=0 ){
//                nums[index++] = num;
                nums[index] = num;
                index++;
            }
        }

        while(index < nums.length){
            nums[index++]=0;
        }
    }

    public static void main(String[] args) {
        int[] a = {0,1,0,3,12};
        MoveZores entity = new MoveZores();
        entity.moveZeros(a);
        for(int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

}
