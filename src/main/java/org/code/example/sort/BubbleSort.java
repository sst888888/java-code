package org.code.example.sort;


import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName BubbleSort
 * @Description
 * @Author Chaiphat
 * @Date 2020/3/5 20:33
 * @Version 1.0
 **/
public class BubbleSort {

    // 快速排序 原地、不稳定的排序算法
    private static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static int partition(int[] a, int start, int end){
        int pivot = a[end];
        int i = start;
        for(int j = start; j < end; j++){
            if(a[j] < pivot){
                swap(a,i,j);
                i++;
            }
        }
        swap(a,i,end);
        return i;
    }

    private static void quick_Sort(int[] nums, int p, int r){
        if(p >= r){
            return;
        }

        int q = partition(nums,p,r);
        quick_Sort(nums,p,q-1);
        quick_Sort(nums,q+1,r);
    }

    public static void quickSort(int[] nums){
        quick_Sort(nums,0,nums.length-1);
    }
/******************************************************************/
    // 归并排序

    // 选择排序  空间复杂度O(1)、平均时间复杂度O(n*2)  性能没有冒泡和插入好


    // 插入排序  空间复杂度O(1)、平均时间复杂度O(n*2)  性能比冒泡好
    public static void insertionSort(int[] a, int n){
        if(n < 1){
            return;
        }

        for(int i = 1; i < n; ++i){
            int value = a[i];
            int j = i -1;
            // 查找插入的位置
            for(; j >= 0; --j){
                if(a[j] > value){
                    a[j+1] = a[j]; // 数据移动
                }else{
                    break;
                }
            }
            a[j+1] = value; // 数据插入
        }
    }

    // 冒泡 空间复杂度O(1)、平均时间复杂度O(n*2)
    public void bubbleSort(int[] a, int n){
        if(n <= 1){return;}

        for(int i = 0; i < n; ++i){
            boolean flag = false;
            for(int j = 0; j < n - i - 1;++j){
                if(a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if(!flag){break;}
        }
    }

    public static void main(String[] args) {

        int size = 50000;
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
            for (int j = 1; j < i; j++) {
                while (arr[i] == arr[j]) {//如果重复，退回去重新生成随机数
                    i--;
                }
            }
        }

       // System.out.println(Arrays.toString(arr));

        long start = System.currentTimeMillis();
        //insertionSort(arr,arr.length);
        System.out.println("插入排序：" + (System.currentTimeMillis() - start));

        long begin = System.currentTimeMillis();
        quickSort(arr);
        System.out.println("快速排序：" + (System.currentTimeMillis() - begin));
        //System.out.println(Arrays.toString(arr));
    }

}
