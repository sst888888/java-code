package com.org.creational.factoryMethod.resourceFactory.impl;

import com.org.creational.factoryMethod.resourceFactory.IResourceLoader;
import com.org.creational.simpleFactory.Resource;

public class FileResourceLoader implements IResourceLoader {
    @Override
    public Resource load(String url) {
        return new Resource(url);
    }
}