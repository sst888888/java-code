package org.code.example.sort;

/**
 * @ClassName CountingSort
 * @Description 计数排序
 * @Author Chaiphat
 * @Date 2020/3/6 17:25
 * @Version 1.0
 **/
public class CountingSort {

    /**
     * 计数排序
     * @param arr 要排序的数组大小
     * @param n  数组元素个数
     */
    public static void sort(int[] arr, int n){
        if(n < 1){
            return;
        }

        // 默认数组最大的元素为数组第一个元素
        int max = arr[0];
        // 遍历数组的所有元素，找到最大的元素
        for(int i = 1; i < n; i++){
            // 若后面的元素大于指定的数组元素，则把元素进行交换
            if(arr[i] > max){
                max = arr[i];
            }
        }

        // 申请一个计数数组，下标从0-max
        int[] c = new int[max + 1];
        // 遍历数组，将每个元素的个数放入到计数数组中，比如分位数为0的元素，在c[0]就累加1，依次类推
        for(int i = 0; i < n; i++){
            c[arr[i]]++;
        }

        // 开始重新整理c[]数组，将c[]数组顺序求和，比如分数0的个数1，分数为1的个数为3。
        // 那么，重新整理后，分数<=0的为1，分数<=1的个数为1+3=4，因为包含了<=0的个数。依次类推
        // 所以，终止条件为 i < max
        for(int i = 1; i <= max; i++){
            c[i] = c[i] + c[i-1];
        }

        // 开始进行排序，创建一个跟要排序的数组一样大小的数据空间
        int[] temp = new int[n];

        // 开始循环要排序的数据
        for(int i = 0; i < n; i++){
            // 计算出需要往temp临时数组哪个索引位置存放arr[i]的值
            // 根据原始数组的值找到计数数组的对应值的计数个数，得到c[arr[i]]的值，也就是temp数组从0开始，所以需要减1
            int index = c[arr[i]] - 1;
            temp[index] = arr[i];
            // 每次循环，计数数组的元素值减1，因为数组放到了temp数组中
            c[arr[i]]--;
        }

        // 重新赋值
        for(int i = 0; i < n; i++){
            arr[i] = temp[i];
        }
    }
}
