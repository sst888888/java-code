package com.example.ob.ex1;

import java.util.ArrayList;
import java.util.List;

public class Subject3D implements Subject{

    private List<Observer> observers = new ArrayList<>();

    private String msg;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        boolean containsed = observers.contains(observer);
        if (containsed) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyAllObserver() {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyAllObserver();
    }
}
