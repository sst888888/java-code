package chapter.searching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.hello-algo.com/chapter_searching/replace_linear_by_hashing/
 * https://github.com/krahets/hello-algo/blob/main/codes/java/chapter_searching/two_sum.java
 */
public class TwoSum {

    /* 方法一：暴力枚举 */
    static int[] twoSumBruteForce(int[] nums, int target) {
        int size = nums.length;
        // 两层循环 时间复杂度为 O（n^2）空间复杂度为 O（1）
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[0];
    }

    /* 方法二：辅助哈希表 */
    static int[] twoSumHashTable(int[] nums, int target) {
        int size = nums.length;
        // 辅助哈希表 空间复杂度为 O（n）
        Map<Integer, Integer> dic = new HashMap<>();
        // 单层循环 时间复杂度为 O（n）
        for (int i = 0; i < size; i++) {
            if (dic.containsKey(target - nums[i])) {
                return new int[] {dic.get(target - nums[i]), i};
            }
            dic.put(nums[i], i);
        }
        return new int[0];
    }


    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 13;

        // 方法一
        int[] res = twoSumBruteForce(nums, target);
        System.out.println("方法一 res = " + Arrays.toString(res));
        // 方法二
        res = twoSumHashTable(nums, target);
        System.out.println("方法二 res = " + Arrays.toString(res));
    }
}
