package org.code.example.interfaceDesign.ocp_button.part01;

/**
 * @ClassName SendButtonDailerAdepter
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/11 20:30
 * @Version 1.0
 **/
public class SendButtonDailerAdepter extends Dialer implements ButtonServer {

    @Override
    public void buttonPressed(int token) {
        super.dial();
    }

}
