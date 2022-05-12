package org.code.example.interfaceDesign.ocp_button.part01;

/**
 * @ClassName Button
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/11 20:28
 * @Version 1.0
 **/
public class Button {

    public ButtonServer buttonServer;
    public Button(ButtonServer buttonServer) {
        this.buttonServer = buttonServer;
    }

}
