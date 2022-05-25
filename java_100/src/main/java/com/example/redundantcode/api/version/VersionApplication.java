package com.example.redundantcode.api.version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class VersionApplication implements WebMvcRegistrations {

    public static void main(String[] args) {
        SpringApplication.run(VersionApplication.class, args);
    }

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new APIVersionHandlerMapping();
    }
}

