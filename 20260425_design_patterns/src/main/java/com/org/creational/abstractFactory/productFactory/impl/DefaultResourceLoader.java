package com.org.creational.abstractFactory.productFactory.impl;

import com.org.creational.abstractFactory.product.AbstractResource;
import com.org.creational.abstractFactory.product.impl.DefaultResource;
import com.org.creational.abstractFactory.productFactory.IResourceLoader;
import com.org.creational.simpleFactory.Resource;

public class DefaultResourceLoader implements IResourceLoader {
    @Override
    public AbstractResource load(String url) {
        return new DefaultResource(url);
    }
}