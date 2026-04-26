package com.org.creational.abstractFactory.product.impl;

import com.org.creational.abstractFactory.product.AbstractResource;

import java.io.InputStream;

public class HttpResource extends AbstractResource {
    public HttpResource(String url) {
        super(url);
    }

    public HttpResource() {
        super();
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }
}
