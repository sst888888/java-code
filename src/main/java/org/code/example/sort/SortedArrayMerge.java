package org.code.example.sort;

/**
 * @ClassName SortedArrayMerge
 * @Description 合并2个有序的数组
 * @Author Chaiphat
 * @Date 2020/3/11 21:19
 * @Version 1.0
 **/
public class SortedArrayMerge<T> {

    public static int[] merge(SortedArray<Integer> array1,SortedArray<Integer> array2){
        int[] result = new int[array1.getSize() + array2.getSize()];
        int i = 0;
        int j = 0;
        int idx = 0;

        while(i < array1.getSize() && j < array2.getSize()){
            if(array1.get(i).compareTo(array2.get(j)) < 0){
                result[idx] = array1.get(i);
                i++;
            }else{
                result[idx] = array2.get(j);
                j++;
            }
            idx++;
        }

        while(i < array1.getSize()){
            result[idx++] = array1.get(i++);
        }

        while(j < array2.getSize()){
            result[idx++] = array2.get(j++);
        }

        return result;
    }


    public static void main(String[] args) {
        SortedArray array = new SortedArray<>(5);
        array.add(1);
        array.add(10);
        array.add(8);
        array.add(100);
        array.add(19);


        SortedArray array2 = new SortedArray<>(15);
        array2.add(5);
        array2.add(8);
        array2.add(8);
        array2.add(7);
        array2.add(309);
        for(int i = 0; i < 10;i++){
            array2.add(i);
        }

        int[] merge = merge(array, array2);
        for(int val : merge){
            System.out.println(val);
        }
    }

}
