package org.code.example.interfaceDesign.isolate;

/**
 * @ClassName Segregation
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 14:45
 * @Version 1.0
 **/
public class Segregation {

    public static void main(String[] args) {
        ClsA clsA = new ClsA();
        clsA.depend1(new ClsD());
        clsA.depend1(new ClsB());
        clsA.depend2(new ClsB());
        clsA.depend3(new ClsB());

        ClsC clsC = new ClsC();
        clsC.depend1(new ClsB());
        clsC.depend1(new ClsD());
        clsC.depend4(new ClsD());
        clsC.depend5(new ClsD());
    }

}
