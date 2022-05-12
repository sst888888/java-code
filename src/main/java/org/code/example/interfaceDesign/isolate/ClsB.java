package org.code.example.interfaceDesign.isolate;

/**
 * @ClassName ClsB
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 12:31
 * @Version 1.0
 **/
public class ClsB implements Interface1,Interface2{
    @Override
    public void operation1() {
        System.out.println("ClsB 实现了operation1");
    }

    @Override
    public void operation2() {
        System.out.println("ClsB 实现了operation2");
    }

    @Override
    public void operation3() {
        System.out.println("ClsB 实现了operation3");
    }
}
