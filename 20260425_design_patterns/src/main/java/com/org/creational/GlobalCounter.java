package com.org.creational;

import java.util.concurrent.atomic.AtomicLong;

public enum GlobalCounter {

    INSTANCE;
    private AtomicLong atomicLong = new AtomicLong(0);

    public long getNumber() {
        return atomicLong.getAndIncrement();
    }

}
