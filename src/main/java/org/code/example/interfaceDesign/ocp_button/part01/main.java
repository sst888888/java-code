package org.code.example.interfaceDesign.ocp_button.part01;

/**
 * @ClassName main
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/11 20:31
 * @Version 1.0
 **/
public class main {

    public static void main(String[] args) {
        new Button(new DigitButtonDailerAdepter()).buttonServer.buttonPressed(9);
        new Button(new DigitButtonDailerAdepter()).buttonServer.buttonPressed(10);
        new Button(new SendButtonDailerAdepter()).buttonServer.buttonPressed(12);
    }

}
