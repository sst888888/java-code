package chapter.searching;

/**
 * 二分查找
 */
public class BinarySearch {

    /* 双闭区间 */
    static int binarySearch(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1] ， 即 i，j 分别指向数组首元素、尾元素
        int i = 0;
        int j = nums.length - 1;
        // 循环， 当所搜区间为空时跳出（当 i > j 时为空）
        while (i <= j) {
            // 计算中点索引 m  防止 i + j超出整型限制 导致异常
            int m = i + (j - i) / 2;
            if (nums[m] < target) {
                // 说明target 在区间 [m + 1, j]中
                i = m + 1;
            } else if (nums[m] > target) {
                // 说明target 在区间 [i, m - 1]中
                j = m - 1;
            } else {
                // 找到目标元素 返回其索引
                return m;
            }
        }
        return -1;
    }

    /* 左闭右开区间 */
    static int binarySearchLCR0(int[] nums, int target) {
        int i = 0;
        int j = nums.length; // 右开 不需要 -1
        while (i < j) {
            int m = i + (j - i) / 2;
            if (nums[m] < target) {
                i = m + 1;
            } else if (nums[m] > target) {
                j = m; // 右开 不需要 -1
            } else {
                return m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int target = 6;
        int[] nums = {1, 3, 6, 8, 12, 15, 23, 26, 31, 35};
        int index = binarySearch(nums, target);
        System.out.println("目标元素6的索引 = " + index);

        index = binarySearchLCR0(nums, target);
        System.out.println("目标元素6的索引 = " + index);
    }

}
