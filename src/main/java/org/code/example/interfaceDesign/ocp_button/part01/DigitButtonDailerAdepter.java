package org.code.example.interfaceDesign.ocp_button.part01;

/**
 * @ClassName DigitButtonDailerAdepter
 * @Description   本类继承Dialer 实现接口ButtonServer
 * @Author Chaiphat
 * @Date 2020/6/11 20:29
 * @Version 1.0
 **/
public class DigitButtonDailerAdepter extends Dialer implements ButtonServer {

    @Override
    public void buttonPressed(int token) {
        // 直接使用父类方法
        super.enterDigit(token);
    }
}
