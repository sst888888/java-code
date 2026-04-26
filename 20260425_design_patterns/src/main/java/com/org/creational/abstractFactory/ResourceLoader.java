package com.org.creational.abstractFactory;

import com.org.creational.abstractFactory.product.AbstractResource;
import com.org.creational.abstractFactory.productFactory.IResourceLoader;
import com.org.creational.simpleFactory.ResourceLoadException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceLoader {


    private static Map<String,IResourceLoader> resourceLoaderCache = new HashMap<>(8);


    static {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("resourceLoader2.properties");
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                String key = entry.getKey().toString();
                Class<?> clazz = Class.forName(entry.getValue().toString());
                IResourceLoader loader = (IResourceLoader) clazz.getConstructor().newInstance();
                resourceLoaderCache.put(key, loader);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public AbstractResource load(String url) {
        String prefix = getPrefix(url);
        return resourceLoaderCache.get(prefix).load(url);
    }

    private String getPrefix(String url) {
        if (url == null || "".equals(url) || !url.contains(":")) {
            throw new ResourceLoadException("此资源url不合法");
        }
        String[] split = url.split(":");
        return split[0];
    }
}
