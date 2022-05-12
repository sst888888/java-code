package org.code.example.interfaceDesign.ocp_button.part03;

/**
 * @ClassName TestAbstract
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/12 10:29
 * @Version 1.0
 **/
public abstract class TestAbstract implements ITestInterface {

    @Override
    public abstract void method1();

    @Override
    public abstract int method2();

    @Override
    public boolean method3(){
        return Boolean.TRUE;
    }

}
