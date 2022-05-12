package org.code.example.interfaceDesign.isolate;

/**
 * @ClassName ClsA
 * @Description ClsA通过接口Interface1\Interface2 依赖（使用）ClsB类，但是只会使用 1，2，3的方法
 * @Author Chaiphat
 * @Date 2020/6/15 12:31
 * @Version 1.0
 **/
public class ClsA {
    public void depend1(Interface1 interface1){
        interface1.operation1();
    }

    public void depend2(Interface2 interface2){
        interface2.operation2();
    }

    public void depend3(Interface2 interface2){
        interface2.operation3();
    }
}
