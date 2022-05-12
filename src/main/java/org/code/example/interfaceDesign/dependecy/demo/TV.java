package org.code.example.interfaceDesign.dependecy.demo;

/**
 * @ClassName TV
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 16:28
 * @Version 1.0
 **/
public class TV implements IButtonServer{
    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {
        System.out.println("关闭电视...");
    }
}
