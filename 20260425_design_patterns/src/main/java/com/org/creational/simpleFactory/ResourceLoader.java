package com.org.creational.simpleFactory;

public class ResourceLoader {

    public Resource load(String url) {
        String prefix = getPrefix(url);

        if ("http".equals(prefix)) {

            return new Resource(url);
        } else if ("file".equals(prefix)) {

            return new Resource(url);
        } else if ("classpath".equals(prefix)) {

            return new Resource(url);
        } else {
            return new Resource(url);
        }

//        return ResourceFactory.create(url, prefix);
    }

    private String getPrefix(String url) {
        if (url == null || "".equals(url) || !url.contains(":")) {
            throw new ResourceLoadException("此资源url不合法");
        }
        String[] split = url.split(":");
        return split[0];
    }
}
