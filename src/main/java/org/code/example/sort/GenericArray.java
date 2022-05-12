package org.code.example.sort;


/**
 * @ClassName GenericArray
 * @Description 一个支持动态扩容的数组
 * @Author Chaiphat
 * @Date 2020/3/11 19:56
 * @Version 1.0
 **/
public class GenericArray<T> {

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

    public GenericArray() {
        this.n = 10;
    }

    public GenericArray(int n) {
        this.n = n;
        data = (T[]) new Object[n];
    }

    /**
     * 检查容量
     */
    private void checkCapacity(){
        if(size == n){
            T[] tmp = (T[])new Object[2*size];
            System.arraycopy(data,0,tmp,0,size);
            n = tmp.length;
            data = tmp;
        }
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
     * 获取数组元素个数
     * @return
     */
    public int getSize(){
        return this.size;
    }

    /**
     * 插入元素
     * @param element
     */
    public void add(T element){
        checkCapacity();
        data[size++] = element;
    }

    /**
     * 指定位置插入
     * @param idx
     * @param element
     */
    public void add(int idx, T element){
        checkCapacity();
        checkIdx(idx);
        for(int i = size - 1; i > idx; i--){
            data[i+1] = data[i];
        }
        data[idx] = element;
        size++;
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

    public static void main(String[] args) {
        GenericArray<Integer> arr = new GenericArray<>(5);
        System.out.println(arr.getSize());
        for(int i = 0; i < arr.n; i++){
            arr.add(i);
        }
        for(int i = 0; i < arr.size; i++){
            System.out.println(arr.get(i));
        }

        arr.add(7);
        System.out.println("size = " + arr.size);
        System.out.println("n = " + arr.n);


        GenericArray array = new GenericArray<Integer>(10);
        for(int i = 0;i < 20;i++){
            array.add(i);
        }
        System.out.println(array.get(18));
        array.delete(18);
        System.out.println(array.get(18));
    }

}
