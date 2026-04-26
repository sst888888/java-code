package com.org.creational.simpleFactory;

public class ResourceLoadException extends RuntimeException{
    public ResourceLoadException() {
        super("加载资源时发生问题");
    }

    public ResourceLoadException(String message) {
        super(message);
    }
}
