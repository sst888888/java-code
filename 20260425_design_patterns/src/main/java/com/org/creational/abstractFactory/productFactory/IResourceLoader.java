package com.org.creational.abstractFactory.productFactory;

import com.org.creational.abstractFactory.product.AbstractResource;

public interface IResourceLoader {

    AbstractResource load(String url);
}
