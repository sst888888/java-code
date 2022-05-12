package org.code.example.interfaceDesign.dependecy.demo;

/**
 * @ClassName Lamp
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 16:03
 * @Version 1.0
 **/
public class Lamp implements IButtonServer {
    @Override
    public void turnOn() {
        System.out.println("打开灯泡...");
    }

    @Override
    public void turnOff() {

    }
}
