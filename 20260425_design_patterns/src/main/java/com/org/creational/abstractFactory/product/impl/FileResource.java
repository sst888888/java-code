package com.org.creational.abstractFactory.product.impl;

import com.org.creational.abstractFactory.product.AbstractResource;

import java.io.InputStream;

public class FileResource extends AbstractResource {
    public FileResource() {
        super();
    }

    public FileResource(String url) {
        super(url);
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }
}