package org.code.example.interfaceDesign.ocp_button.part02;

import org.code.example.interfaceDesign.ocp_button.part01.Dialer;

/**
 * @ClassName Phone
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/11 21:01
 * @Version 1.0
 **/
public class Phone {
    private Dialer dialer;
    private Button[] digitButtons;
    private Button sendButton;

    public Phone() {
        dialer = new Dialer();
        digitButtons = new Button[10];
        for (int i = 0; i < digitButtons.length; i++) {
            digitButtons[i] = new Button();
            final int digit = i;
            digitButtons[i].addListener(()-> {dialer.enterDigit(digit);});
            sendButton = new Button();
            sendButton.addListener(()->{dialer.dial();});
        }
    }

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.digitButtons[3].press();
        phone.digitButtons[2].press();
        phone.digitButtons[1].press();
        phone.sendButton.press();
    }

}
