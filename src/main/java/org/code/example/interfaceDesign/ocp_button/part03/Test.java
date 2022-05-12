package org.code.example.interfaceDesign.ocp_button.part03;

/**
 * @ClassName Test
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/12 10:42
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        TestAbstract cls = new TestClass1();
        cls.method1();
        TestAbstract cls2 = new TestClass2();
        cls2.method1();
        System.out.println(cls2.method3());
    }

}
