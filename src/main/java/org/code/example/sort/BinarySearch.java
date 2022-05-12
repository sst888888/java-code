package org.code.example.sort;

/**
 * @ClassName BinarySearch
 * @Description 二分查找
 * @Author Chaiphat
 * @Date 2020/3/6 21:38
 * @Version 1.0
 **/
public class BinarySearch {

    /**
     * 在不存在重复元素的有序数组中，查找值等于给定值的元素。  最简单的二分查找
     * @param a
     * @param low
     * @param high
     * @param value
     * @return
     */
    private static int bsearchInternally(int[] a, int low, int high, int value){
        if(low > high){
            return -1;
        }

        // 位运算 性能高  先求最大、最小的差值，再取平均值，再加上最下值
        // (low+high)/2 这种写法有可能导致溢出，比如low和high都很大，之和就溢出了。
        int mid = low + ((high - low) >> 1);
        if(a[mid] == value){
            return mid;
        }else if (a[mid] < value){
            return bsearchInternally(a,mid + 1,high,value);
        }else{
            return bsearchInternally(a,low,mid-1,value);
        }
    }


    // 递归实现
    public static int bsearch(int[] a, int n, int val){
        return bsearchInternally(a,0,n-1,val);
    }

    // 普通实现
    public int search(int[] a, int n, int val){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = (low + high) >> 1;
            if(a[mid] == val){
                return mid;
            }else if (a[mid] < val){
                low = mid + 1;
            }else {
                high = mid -1;
            }
        }
        return -1;
    }


    /**
     * 查找第一个值等于给定值的元素
     * 有序数据集合中存在重复的数据 [1,3,4,5,6,8,8,8,11,18]
     * @param a
     * @param n
     * @param val
     * @return
     */
    public static int bsearchFirst(int[] a, int n, int val){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low ) >> 1);
            if(a[mid] > val){
                high = mid - 1;
            }else if (a[mid] < val){
                low = mid + 1;
            }else {
                if((mid == 0) || (a[mid-1] != val)){
                    return mid;
                }else{
                    high = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * 查找最后一个值等于给定值的元素
     * 有序数据集合中存在重复的数据 [1,3,4,5,6,8,8,8,11,18]
     * @param a
     * @param n
     * @param val
     * @return
     */
    public int bsearchLast(int[] a, int n, int val){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low ) >> 1);
            if(a[mid] > val){
                high = mid - 1;
            }else if (a[mid] < val){
                low = mid + 1;
            }else {
                if((mid == n-1) || (a[mid+1] != val)){
                    return mid;
                }else{
                    low = mid + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 查找第一个大于等于给定值的元素
     * 有序数据集合中存在重复的数据 [1,3,4,5,6,8,8,8,11,18]
     * @param a
     * @param n
     * @param val
     * @return
     */
    public int bsearch4First(int[] a, int n, int val){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low ) >> 1);
            if(a[mid] > val){
                if((mid == 0) || (a[mid - 1] < val)){
                    return mid;
                }else{
                    high = mid - 1;
                }
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 查找最后一个小于等于给定值的元素
     * 有序数据集合中存在重复的数据 [1,3,4,5,6,8,8,8,11,18]
     * @param a
     * @param n
     * @param val
     * @return
     */
    public int bsearch4Last(int[] a, int n, int val){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low ) >> 1);
            if(a[mid] > val){
                high = mid - 1;
            }else{
                if((mid == n - 1) || (a[mid + 1] > val)){
                    return mid;
                }else{
                    low = mid + 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {1,3,4,5,8,8,8,8,11,18};
        int index = bsearchFirst(arr, arr.length, 8);
        System.out.println(index);
    }


}
