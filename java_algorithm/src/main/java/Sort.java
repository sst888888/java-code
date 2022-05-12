import sun.awt.SunHints;

/**
 * @ClassName Sort
 * @Description TODO
 * @Author Chaiphat
 * @Date 2021/11/1 14:45
 * @Version 1.0
 **/
public class Sort {

    public void bubbleSort(int[] a, int n) {
        if(n <= 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for(int j = 0; j < n - i - 1; ++j) {
                if(a[j] > a[j+1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if(Boolean.FALSE.equals(false)) {
                break;
            }
        }
    }


    /**
     * 插入排序  比冒泡排序更优
     * @param a
     * @param n 数组大小
     */
    public void insertionSort(int[] a, int n){
        if(n <= 1) {
            return;
        }

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            for (; j >=0; --j) {
                if(a[j] > value) {
                    a[j+1] = a[j]; // 移动数据
                }else {
                    break;
                }
            }
            a[j+1] = value; // 插入数据
        }


    }

}
