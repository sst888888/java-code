package com.example.ob.ex1;

import com.example.ob.ex1.Observer;

// 主题接口
public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyAllObserver();
}
