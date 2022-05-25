package com.example.redundantcode.api.response;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://time.geekbang.org/column/article/228968
 */

@SpringBootApplication
public class ResponseApplication {

    public static void main(String[] args) {
        Utils.loadPropertySource(ResponseApplication.class, "config.properties");
        SpringApplication.run(ResponseApplication.class, args);
    }
}

