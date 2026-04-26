package com.org.creational.factoryMethod.resourceFactory;

import com.org.creational.simpleFactory.Resource;

public interface IResourceLoader {

    Resource load(String url);
}
