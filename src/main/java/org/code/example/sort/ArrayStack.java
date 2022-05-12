package org.code.example.sort;

/**
 * @ClassName ArrayStack
 * @Description 数组实现一个顺序栈
 * @Author Chaiphat
 * @Date 2020/3/19 21:36
 * @Version 1.0
 **/
public class ArrayStack {

    private String[] data;
    private int count;
    private int size;

    public ArrayStack(int n){
        this.data = new String[n];
        this.count = 0;
        this.size = n;
    }

    public boolean push(String value){
        if(count == size){
            return false;
        }else{
            data[count] = value;
            count++;
            return true;
        }
    }

    public String pool(){
        if(count == 0){
            return null;
        }else{
            count--;
            return data[count];
        }
    }

    public static void main(String[] args){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c==d);
        System.out.println(e==f);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g ==(a+b));
        System.out.println(g.equals(a+b));
    }

}
