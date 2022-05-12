package org.code.example.sort;

/**
 * @ClassName SortedArray
 * @Description 一个大小固定的有序数组
 * @Author Chaiphat
 * @Date 2020/3/11 20:47
 * @Version 1.0
 **/
public class SortedArray<T extends Comparable> {

    /**
     * 存储数据
     */
    private T[] data;

    /**
     * 元素个数
     */
    private int size;

    /**
     * 容量
     */
    private int n;

    public SortedArray() {
    }

    public SortedArray(int n) {
        this.n = n;
        data = (T[])new Comparable[n];
    }

    /**
     * 检测容量
     */
    private void checkCapacity(){
        if(this.size == this.n){
            throw new RuntimeException("空间不足");
        }
    }

    /**
     * 返回数组大小
     * @return
     */
    public int getSize(){
        return this.size;
    }


    private void checkIdx(int idx){
        if(idx < 0 || idx >= n){
            throw new IllegalArgumentException(idx + "不是有效的下标位置");
        }
    }

    /**
     * 返回下标idx的元素
     * @param idx
     * @return
     */
    public T get(int idx){
        checkIdx(idx);
        return data[idx];
    }


    /**
     * 删除并返回下标idx的元素
     * @param idx
     * @return
     */
    public T delete(int idx){
        checkIdx(idx);
        T tmp = data[idx];
        for(int i = idx + 1; i < size;i++){
            data[i-1] = data[i];
        }
        size--;
        return tmp;
    }

    /**
     * 插入元素
     * @param element
     */
    public void add(T element){
        checkCapacity();
        int idx = size;
        for(int i = 0; i < size; i++){
            if(element.compareTo(data[i]) < 0){
                idx = i;
                break;
            }
        }

        for(int i = size - 1; i >= idx; i--){
            data[i+1] = data[i];
        }
        data[idx] = element;
        size++;
    }

    private void printAll(){
        System.out.println("size = " + size);
        for(int i = 0; i < size; i++){
            System.out.println(this.get(i));
        }
    }

    public static void main(String[] args){
        SortedArray<Integer> array = new SortedArray<>(5);
        array.add(1);
        array.add(10);
        array.add(8);
        array.add(100);
        array.add(19);

        array.printAll();
        array.delete(3);
        array.printAll();
    }

}
