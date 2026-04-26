package com.org.creational.prototype.deepCopy;

public class Product implements Cloneable {
    private String name;
    private double price;
    private int stock;

    // 省略构造函数、getter和setter方法

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
