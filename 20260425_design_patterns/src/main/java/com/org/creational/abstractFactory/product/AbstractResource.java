package com.org.creational.abstractFactory.product;

import java.io.InputStream;

public abstract class AbstractResource {

    private String url;

    public AbstractResource() {}

    public AbstractResource(String url) {
        this.url = url;
    }


    protected void shared() {
        System.out.println("只让子类实现");
    }

    public abstract InputStream getInputStream();

    public String getUrl() {
        return url;
    }
}
