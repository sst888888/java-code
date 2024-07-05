import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName Sort
 * @Description
 * @Author
 * @Date
 * @Version 1.0
 **/
public class Sort {

    public int[] bubbleSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < arr.length; i++) {
            boolean flag = true;

            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }

        return arr;
    }


    /**
     * 插入排序  比冒泡排序更优
     * arr[8,5,3,2,9,6]
     */
    public int[] insertionSort(int[] sourceArray){
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < arr.length; i++) {
            // 要插入的数据
            int tmp = arr[i]; // 3

            int j = i; // 2
            while (j > 0 && tmp < arr[j - 1]) { // arr[1]
                // arr[j]赋值
                arr[j] = arr[j - 1]; // arr[8,8,3,2,9,6]
                j--;
            }

            //
            if (j != i) { // j = 1, i = 2
                arr[j] = tmp; // arr[5,8,3,2,9,6]
            }
        }
        return arr;
    }

    // arr[8,5,3,2,9,6]
    public static int[] insertionSort2(int[] a){
        if (a.length <= 1) {
            return a;
        }

        for (int i = 1; i < a.length; ++i) {
            int value = a[i]; // i = 1  value = 5
            int j = i - 1; // j = 0
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) { // 8 > 5
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            a[j+1] = value;
        }
        return a;
    }


    /**
     * 希尔排序，插入排序的优化
     */
    public int[] shellSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int gap = 1;
        while (gap < arr.length/3) {
            gap = gap * 3 + 1;
        }

        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }

        return arr;
    }

    /**
     * 选择排序
     */
    public int[] selectionSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i; // 记录的是最小值 最小值的位置

            // 循环比较 i后面的值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            // 找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }

        return arr;
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left,1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right,1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left,1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right,1, right.length);
        }

        return result;
    }

    /**
     * 归并排序
     */
    public int[] mergeSort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr,0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 交换数组内两个元素
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex) {
                    swap(array, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }

    /**
     * 快速排序
     */
    public int[] quickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) {
            return null;
        }

        int smallIndex = partition(array, start, end);
        if (smallIndex > start) {
            quickSort(array, start, smallIndex - 1);
        }

        if (smallIndex < end) {
            quickSort(array, smallIndex + 1, end);
        }
        return array;
    }


    /**
     * 调整使之成为最大堆
     * @param array
     * @param i
     */
    public static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        int length = array.length;
        // 如果有左子树 且左子树大于父节点 则将最大指针指向左子树
        if (i * 2 < length && array[i * 2] > array[maxIndex]) {
            maxIndex = i * 2;
        }
        // 如果有右子树 且右子树大于父节点 则将最大指针指向右子树
        if (i * 2 + 1 < length && array[i * 2 + 1] > array[maxIndex]) {
            maxIndex = i * 2 + 1;
        }
        // 如果父节点不是最大值 则将父节点与最大值交换 并且递归调整与父节点交换的位置
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }

    public static void buildMaxHeap(int[] array) {
        for (int i = (array.length/2 - 1); i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    /**
     * 堆排序算法
     * @param array
     * @return
     */
    public static int[] heapSort(int[] array) {
        int length = array.length;
        if (length < 1) {
            return array;
        }
        buildMaxHeap(array);
        while (length > 0){
            swap(array, 0, length - 1);
            length--;
            adjustHeap(array, 0);
        }
        return array;
    }

    public static int[] countingSort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        int bias;
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        bias = 0 - min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bias]++;
        }
        int index = 0;
        int i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else {
                i++;
            }
        }

        return array;
    }

    /**
     * 基数排序
     * @param array
     * @return
     */
    public static int[] radixSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }

        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }

        int mod = 10;
        int div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<>());
        }
        for (int i = 0; i< maxDigit; i++, mod*=10,div*=10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }

            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    array[index++] = bucketList.get(j).get(k);
                    bucketList.get(j).clear();
                }
            }
        }
        return array;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = {8,5,3,2,9,6};
        Sort sort = new Sort();
        int[] bubbledSort = sort.bubbleSort(arr);
        for (int i : bubbledSort) {
            System.out.println(i);
        }

        bubbledSort = sort.insertionSort(arr);
        bubbledSort = sort.selectionSort(arr);
        bubbledSort = sort.shellSort(arr);
        bubbledSort = sort.mergeSort(arr);
        bubbledSort = sort.quickSort(arr, 0, 5);
        bubbledSort = sort.heapSort(arr);
        bubbledSort = sort.countingSort(arr);
        bubbledSort = sort.radixSort(arr);

        int[] arr2 = {8,5,3,2,9,6};
        bubbledSort = sort.insertionSort2(arr2);
        for (int i : bubbledSort) {
            System.out.println(i);
        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement: stackTrace) {
            System.out.println(stackTraceElement.getClassName());
        }

    }

}
