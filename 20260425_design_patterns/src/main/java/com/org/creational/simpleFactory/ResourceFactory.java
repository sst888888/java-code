package com.org.creational.simpleFactory;

public class ResourceFactory {

    public static Resource create(String url, String type) {
        if ("http".equals(type)) {

            return new Resource(url);
        } else if ("file".equals(type)) {

            return new Resource(url);
        } else if ("classpath".equals(type)) {

            return new Resource(url);
        } else {
            return new Resource(url);
        }

    }
}
