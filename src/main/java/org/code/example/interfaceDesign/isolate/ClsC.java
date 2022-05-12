package org.code.example.interfaceDesign.isolate;

/**
 * @ClassName ClsC
 * @Description ClsC通过接口Interface1\Interface3 依赖（使用）ClsD类，但是只会使用 1，4，5的方法
 * @Author Chaiphat
 * @Date 2020/6/15 12:31
 * @Version 1.0
 **/
public class ClsC {
    public void depend1(Interface1 interface1){
        interface1.operation1();
    }

    public void depend4(Interface3 interface3){
        interface3.operation4();
    }

    public void depend5(Interface3 interface3){
        interface3.operation5();
    }
}
