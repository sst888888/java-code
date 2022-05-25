package com.example.redundantcode.api.response;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://time.geekbang.org/column/article/228968
 */

@SpringBootApplication
public class CommonMistakesApplication {

    public static void main(String[] args) {
        Utils.loadPropertySource(CommonMistakesApplication.class, "config.properties");
        SpringApplication.run(CommonMistakesApplication.class, args);
    }
}

