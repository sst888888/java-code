package org.code.example.interfaceDesign.isolate;

/**
 * @ClassName ClsD
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 12:32
 * @Version 1.0
 **/
public class ClsD implements Interface1,Interface3{
    @Override
    public void operation1() {
        System.out.println("ClsD 实现了operation1");
    }

    @Override
    public void operation4() {
        System.out.println("ClsD 实现了operation4");
    }

    @Override
    public void operation5() {
        System.out.println("ClsD 实现了operation5");
    }
}
