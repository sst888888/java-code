package org.code.example.interfaceDesign.ocp_button.part02;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Button
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/11 20:59
 * @Version 1.0
 **/
public class Button {

    public List<ButtonListener> listeners;
    public Button() {
        this.listeners = new LinkedList<ButtonListener>();
    }

    public void addListener(ButtonListener listener){
        this.listeners.add(listener);
    }

    public void press(){
        for (ButtonListener listener : listeners) {
            listener.buttonPressed();
        }
    }

}
