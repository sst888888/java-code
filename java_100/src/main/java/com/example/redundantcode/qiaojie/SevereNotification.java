package com.example.redundantcode.qiaojie;

public class SevereNotification extends Notification{


    public SevereNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {
        msgSender.send(message);
    }
}
