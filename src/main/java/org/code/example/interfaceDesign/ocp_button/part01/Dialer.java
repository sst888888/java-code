package org.code.example.interfaceDesign.ocp_button.part01;

/**
 * @ClassName Dialer
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/11 20:27
 * @Version 1.0
 **/
public class Dialer {

    public void enterDigit(int digit) {
        System.out.println("enter digit: " + digit);
    }

    public void dial() {
        System.out.println("dialing...");
    }

}
