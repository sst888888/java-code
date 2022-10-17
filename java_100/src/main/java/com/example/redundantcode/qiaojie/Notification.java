package com.example.redundantcode.qiaojie;

/**
 * @link  https://time.geekbang.org/column/article/202786
 */
public abstract class Notification {

    protected MsgSender msgSender;

    public Notification(MsgSender msgSender) { this.msgSender = msgSender; }


    public abstract void notify(String message);

}
