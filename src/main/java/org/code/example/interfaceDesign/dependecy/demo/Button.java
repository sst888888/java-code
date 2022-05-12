package org.code.example.interfaceDesign.dependecy.demo;

/**
 * @ClassName Button
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 16:04
 * @Version 1.0
 **/
public class Button {

    public IButtonServer iButtonServer;

    public TV tv;

    public Button(TV tv) {
        this.tv = tv;
    }

    public void setiButtonServer(IButtonServer iButtonServer) {
        this.iButtonServer = iButtonServer;
    }

    public void poll(){
        this.iButtonServer.turnOn();
        this.tv.turnOff();
    }
}
