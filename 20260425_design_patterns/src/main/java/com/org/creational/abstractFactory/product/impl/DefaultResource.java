package com.org.creational.abstractFactory.product.impl;

import com.org.creational.abstractFactory.product.AbstractResource;

import java.io.InputStream;

public class DefaultResource extends AbstractResource {

    public DefaultResource() {
        super();
    }

    public DefaultResource(String url) {
        super(url);
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }
}