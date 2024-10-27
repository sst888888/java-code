package org.cp.springframework.core.io;

/**
 * @author: cp
 * @date: 2024-10-27 17:01
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);


}
