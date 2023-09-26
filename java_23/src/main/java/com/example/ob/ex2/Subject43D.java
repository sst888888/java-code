package com.example.ob.ex2;

import java.util.Observable;


public class Subject43D extends Observable {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        setChanged();
        notifyObservers();
    }

}
