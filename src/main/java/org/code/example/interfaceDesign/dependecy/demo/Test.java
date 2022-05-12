package org.code.example.interfaceDesign.dependecy.demo;

/**
 * @ClassName Test
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 16:09
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        Button button = new Button(new TV());
        // setter方式实现
        button.setiButtonServer(new Lamp());
        button.poll();
    }
}
